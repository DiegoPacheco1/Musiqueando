package com.example.hospitalcompose.ui.pantallas.artistas

import com.example.hospitalcompose.domain.modelo.Artista
import com.example.hospitalcompose.ui.utils.common.UiEvent


interface ArtistasContract {
    sealed class ArtistasEvent {
        data object LoadArtistas : ArtistasEvent()
        data object UiEventDone  : ArtistasEvent()
    }
    data class ArtistasState(
        val isLoading: Boolean = false,
        val artistas:   List<Artista>? = emptyList(),
        val uiEvent:    UiEvent?     = null
    )
}
