package com.example.hospitalcompose.ui.pantallas.afinador

object AfinadorContract {
    data class State(
        val pitchHz: Float = 0f,
        val note: String = "",
        val cents: Float = 0f,
        val isTuning: Boolean = false
    )

    sealed class Event {
        data object Start : Event()
        data object Stop  : Event()
    }
}
