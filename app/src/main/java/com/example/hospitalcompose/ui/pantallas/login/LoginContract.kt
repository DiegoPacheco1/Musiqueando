package com.example.hospitalcompose.ui.pantallas.login

import com.example.hospitalcompose.ui.utils.common.UiEvent

interface LoginContract {

    sealed class LoginEvent {
        data class Login(val nombre: String, val password: String) : LoginEvent()
        data class Register(val usuario: String, val password: String) : LoginEvent()
        data object UiEventDone : LoginEvent()
        data class UpdateLoginMode(val isLogin: Boolean) : LoginEvent()
        data class UpdateUsername(val nombre: String) : LoginEvent()
        data class UpdatePassword(val password: String) : LoginEvent()
    }

    data class LoginState(
        val isLoading: Boolean = false,
        val uiEvent: UiEvent? = null,
        val isLoginMode: Boolean = true,
        val nombre: String = "",
        val password: String = ""
    )
}
