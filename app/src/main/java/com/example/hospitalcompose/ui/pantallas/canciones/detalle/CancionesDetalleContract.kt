// CancionesDetalleContract.kt
package com.example.hospitalcompose.ui.pantallas.canciones.detalle

import com.example.hospitalcompose.domain.modelo.Cancion
import com.example.hospitalcompose.ui.utils.common.UiEvent

interface CancionesDetalleContract {

    sealed class DetalleEvent {
        data class LoadDetalle(val id: String) : DetalleEvent()
        object TransposeUp    : DetalleEvent()
        object TransposeDown  : DetalleEvent()
        object UiEventDone    : DetalleEvent()
    }

    data class DetalleState(
        val isLoading: Boolean           = false,
        val cancion: Cancion?            = null,
        val semitoneShift: Int           = 0,
        val shiftedAcordes: List<String> = emptyList(),
        val uiEvent: UiEvent?            = null
    )
}
