package com.example.hospitalcompose.domain.usecases.usuarios

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.UsuariosRepository
import com.example.hospitalcompose.domain.modelo.RegistroRequest
import javax.inject.Inject

class RegistrarUsuarioUseCase @Inject constructor(
    private val usuariosRepository: UsuariosRepository
) {
    suspend operator fun invoke(request: RegistroRequest): NetworkResult<String> {
        return usuariosRepository.registrarUsuario(request)
    }
}
