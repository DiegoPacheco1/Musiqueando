package com.example.hospitalcompose.ui.pantallas.artistas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.domain.usecases.artistas.ObtenerArtistasUseCase
import com.example.hospitalcompose.ui.utils.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistasViewModel @Inject constructor(
    private val obtenerArtistas: ObtenerArtistasUseCase
): ViewModel() {
    private val _ui = MutableStateFlow(ArtistasContract.ArtistasState())
    val ui: StateFlow<ArtistasContract.ArtistasState> = _ui

    fun handleEvent(e: ArtistasContract.ArtistasEvent) {
        when(e) {
            ArtistasContract.ArtistasEvent.LoadArtistas -> load()
            ArtistasContract.ArtistasEvent.UiEventDone  ->
                _ui.update{ it.copy(uiEvent = null) }
        }
    }

    private fun load() = viewModelScope.launch {
        _ui.update { it.copy(isLoading = true) }
        when(val r = obtenerArtistas()) {
            is NetworkResult.Success -> _ui.update {
                it.copy(isLoading = false, artistas = r.data)
            }
            is NetworkResult.Error   -> _ui.update {
                it.copy(isLoading = false,
                    uiEvent = UiEvent.ShowSnackbar(r.message ?: "Error"))
            }
            is NetworkResult.Loading -> Unit
        }
    }
}

