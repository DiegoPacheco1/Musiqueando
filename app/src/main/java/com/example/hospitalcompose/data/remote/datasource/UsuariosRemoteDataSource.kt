package com.example.hospitalcompose.data.remote.datasource


import com.example.hospitalcompose.common.Constantes
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.apiServices.UsuariosService
import com.example.hospitalcompose.data.remote.datasource.utils.BaseApiResponse
import com.example.hospitalcompose.domain.modelo.LoginRequest
import com.example.hospitalcompose.domain.modelo.RegistroRequest
import com.example.hospitalcompose.domain.modelo.TokenResponse
import retrofit2.Response
import javax.inject.Inject


class UsuariosRemoteDataSource @Inject constructor(
    private val usuariosService: UsuariosService
) : BaseApiResponse() {

    suspend fun registerUser(request: RegistroRequest): NetworkResult<String> {
        val result = safeApiCall {
            val response = usuariosService.registerUser(request)
            val message = response.body()?.string() ?: Constantes.ERROR_EN_LA_CONVERSION
            Response.success(message)
        }
        return result
    }

    suspend fun loginUser(request: LoginRequest): NetworkResult<TokenResponse> {
        val response = usuariosService.loginUser(request)
        return safeApiCall { response }
    }
}
