package com.example.hospitalcompose.ui.pantallas.canciones.listado

import com.example.hospitalcompose.domain.modelo.Cancion
import com.example.hospitalcompose.ui.utils.common.UiEvent

interface CancionesContract {

    sealed class CancionesEvent {
        object LoadCanciones : CancionesEvent()
        object UiEventDone   : CancionesEvent()
    }

    data class CancionesState(
        val isLoading: Boolean = false,
        val canciones: List<Cancion> = emptyList(),
        val uiEvent: UiEvent? = null
    )
}
