// CancionesViewModel.kt
package com.example.hospitalcompose.ui.pantallas.canciones.listado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.domain.usecases.canciones.ObtenerCancionesUseCase
import com.example.hospitalcompose.ui.utils.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancionesViewModel @Inject constructor(
    private val obtenerCancionesUseCase: ObtenerCancionesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CancionesContract.CancionesState())
    val uiState: StateFlow<CancionesContract.CancionesState> = _uiState

    fun handleEvent(event: CancionesContract.CancionesEvent) {
        when (event) {
            CancionesContract.CancionesEvent.LoadCanciones -> loadCanciones()
            CancionesContract.CancionesEvent.UiEventDone   -> clearUiEvent()
        }
    }

    private fun loadCanciones() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        when (val result = obtenerCancionesUseCase()) {
            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            is NetworkResult.Success -> _uiState.update {
                it.copy(
                    isLoading = false,
                    canciones = result.data.orEmpty()
                )
            }
            is NetworkResult.Error   -> _uiState.update {
                it.copy(
                    isLoading = false,
                    uiEvent = UiEvent.ShowSnackbar(result.message ?: "Error al cargar canciones")
                )
            }
        }
    }

    private fun clearUiEvent() {
        _uiState.update { it.copy(uiEvent = null) }
    }
}
