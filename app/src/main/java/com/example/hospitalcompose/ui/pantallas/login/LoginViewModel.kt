package com.example.hospitalcompose.ui.pantallas.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalcompose.common.Constantes
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.domain.modelo.LoginRequest
import com.example.hospitalcompose.domain.modelo.RegistroRequest
import com.example.hospitalcompose.domain.usecases.usuarios.LoginUsuarioUseCase
import com.example.hospitalcompose.domain.usecases.usuarios.RegistrarUsuarioUseCase
import com.example.hospitalcompose.ui.utils.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsuarioUseCase: LoginUsuarioUseCase,
    private val registrarUsuarioUseCase: RegistrarUsuarioUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginContract.LoginState())
    val uiState: StateFlow<LoginContract.LoginState> = _uiState

    fun handleEvent(event: LoginContract.LoginEvent) {
        when (event) {
            is LoginContract.LoginEvent.Login ->
                login(event.nombre, event.password)

            is LoginContract.LoginEvent.Register ->
                register(event.usuario, event.password)

            LoginContract.LoginEvent.UiEventDone ->
                clearUiEvents()

            is LoginContract.LoginEvent.UpdateLoginMode ->
                updateLoginMode(event.isLogin)

            is LoginContract.LoginEvent.UpdateUsername ->
                updateUsername(event.nombre)

            is LoginContract.LoginEvent.UpdatePassword ->
                updatePassword(event.password)
        }
    }

    private fun login(nombre: String, password: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            when (loginUsuarioUseCase(LoginRequest(nombre, password))) {
                is NetworkResult.Loading ->
                    _uiState.value = _uiState.value.copy(isLoading = true)

                is NetworkResult.Success ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        uiEvent = UiEvent.ShowSnackbar(Constantes.CREDENCIALES_INCORRECTAS)
                    )


                is NetworkResult.Error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        uiEvent = UiEvent.Navigate()
                    )
            }
        }
    }

    private fun register(usuario: String, password: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = registrarUsuarioUseCase(RegistroRequest(usuario, password))) {
                is NetworkResult.Loading ->
                    _uiState.value = _uiState.value.copy(isLoading = true)

                is NetworkResult.Success ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        uiEvent = UiEvent.ShowSnackbar(result.data ?: Constantes.REGISTRO_EXITOSO)
                    )

                is NetworkResult.Error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        uiEvent = UiEvent.ShowSnackbar(result.message ?: Constantes.ERROR_DESCONOCIDO)
                    )
            }
        }
    }

    private fun clearUiEvents() {
        _uiState.value = _uiState.value.copy(uiEvent = null)
    }

    private fun updateLoginMode(isLogin: Boolean) {
        _uiState.value = _uiState.value.copy(isLoginMode = isLogin)
    }

    private fun updateUsername(nombre: String) {
        _uiState.value = _uiState.value.copy(nombre = nombre)
    }

    private fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }
}
