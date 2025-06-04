package com.example.hospitalcompose.domain.usecases.usuarios

import com.example.hospitalcompose.data.remote.repositories.UsuariosRepository
import com.example.hospitalcompose.domain.modelo.LoginRequest

import javax.inject.Inject

class LoginUsuarioUseCase @Inject constructor(
    private val usuariosRepository: UsuariosRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest) = usuariosRepository.loginUsuario(loginRequest)
}