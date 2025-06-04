// CancionesDetalleViewModel.kt
package com.example.hospitalcompose.ui.pantallas.canciones.detalle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.domain.usecases.canciones.ObtenerDetalleCancionUseCase
import com.example.hospitalcompose.ui.utils.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancionesDetalleViewModel @Inject constructor(
    private val obtenerDetalleUseCase: ObtenerDetalleCancionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CancionesDetalleContract.DetalleState())
    val uiState: StateFlow<CancionesDetalleContract.DetalleState> = _uiState

    // Escalas cromáticas en español e inglés
    private val chromaticEN = listOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")
    private val chromaticES = listOf("Do","Do#","Re","Re#","Mi","Fa","Fa#","Sol","Sol#","La","La#","Si")

    // Todas las raíces posibles, ordenadas por longitud descendente
    private val allRoots = (chromaticES + chromaticEN)
        .distinct()
        .sortedByDescending { it.length }

    fun handleEvent(event: CancionesDetalleContract.DetalleEvent) {
        when (event) {
            is CancionesDetalleContract.DetalleEvent.LoadDetalle -> loadDetalle(event.id)
            CancionesDetalleContract.DetalleEvent.TransposeUp   -> transpose(+1)
            CancionesDetalleContract.DetalleEvent.TransposeDown -> transpose(-1)
            CancionesDetalleContract.DetalleEvent.UiEventDone   -> clearUiEvent()
        }
    }

    private fun loadDetalle(id: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        when (val res = obtenerDetalleUseCase(id)) {
            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            is NetworkResult.Success -> {
                val song    = res.data
                val base     = song?.acordes.orEmpty().distinct()
                _uiState.update {
                    it.copy(
                        isLoading      = false,
                        cancion        = song,
                        semitoneShift  = 0,
                        shiftedAcordes = base
                    )
                }
            }
            is NetworkResult.Error -> _uiState.update {
                it.copy(
                    isLoading = false,
                    uiEvent   = UiEvent.ShowSnackbar(res.message ?: "Error cargando detalle")
                )
            }
        }
    }

    private fun transpose(delta: Int) = viewModelScope.launch {
        _uiState.update { st ->
            val newShift = (st.semitoneShift + delta).floorMod(12)
            val original = st.cancion?.acordes.orEmpty().distinct()
            val transposed = original.map { chord -> transposeChord(chord, newShift) }
            st.copy(
                semitoneShift  = newShift,
                shiftedAcordes = transposed
            )
        }
    }

    private fun clearUiEvent() {
        _uiState.update { it.copy(uiEvent = null) }
    }

    /** Transporta un acorde n semitonos arriba (shift>0) o abajo (shift<0). */
    private fun transposeChord(chord: String, shift: Int): String {
        // 1) Encontrar raíz
        val root = allRoots.firstOrNull { chord.startsWith(it) } ?: return chord
        val suffix = chord.removePrefix(root)

        // 2) Determinar escala y posición original
        val (scale, idx) = when {
            chromaticES.contains(root) -> chromaticES to chromaticES.indexOf(root)
            chromaticEN.contains(root) -> chromaticEN to chromaticEN.indexOf(root)
            else -> return chord
        }

        // 3) Calcular nueva raíz
        val newRoot = scale[(idx + shift).floorMod(12)]

        // 4) Reconstruir
        return newRoot + suffix
    }

    /** Módulo euclidiano: (-1).floorMod(12) == 11 */
    private fun Int.floorMod(other: Int): Int =
        ((this % other) + other) % other
}
