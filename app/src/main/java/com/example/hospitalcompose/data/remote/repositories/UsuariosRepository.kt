package com.example.hospitalcompose.data.remote.repositories

import com.example.hospitalcompose.common.Constantes
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.datasource.UsuariosRemoteDataSource
import com.example.hospitalcompose.data.utils.TokenManager
import com.example.hospitalcompose.domain.modelo.LoginRequest
import com.example.hospitalcompose.domain.modelo.RegistroRequest
import javax.inject.Inject

class UsuariosRepository @Inject constructor(
    private val usuariosRemoteDataSource: UsuariosRemoteDataSource,
    private val tokenManager: TokenManager
) {

    suspend fun registrarUsuario(request: RegistroRequest): NetworkResult<String> {
        return usuariosRemoteDataSource.registerUser(request)
    }

    suspend fun loginUsuario(request: LoginRequest): NetworkResult<String> {
        return try {
            when (val resultado = usuariosRemoteDataSource.loginUser(request)) {
                is NetworkResult.Success -> {
                    resultado.data?.let {
                        tokenManager.saveTokens(it.accessToken, it.refreshToken)
                    }
                    NetworkResult.Success(Constantes.LOGIN_EXITOSO)
                }
                is NetworkResult.Error -> {
                    NetworkResult.Error(resultado.message ?: Constantes.ERROR_DESCONOCIDO)
                }
                is NetworkResult.Loading -> NetworkResult.Loading()
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: Constantes.ERROR_DESCONOCIDO_EN_EL_LOGIN)
        }
    }
}
