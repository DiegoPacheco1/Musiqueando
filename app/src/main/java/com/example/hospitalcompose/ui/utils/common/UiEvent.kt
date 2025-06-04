package com.example.hospitalcompose.ui.utils.common

sealed class UiEvent{

    data class Navigate(val route: String=""): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()

}
