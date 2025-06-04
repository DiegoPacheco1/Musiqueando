package com.example.hospitalcompose.data.remote.apiServices

import com.example.hospitalcompose.domain.modelo.LoginRequest
import com.example.hospitalcompose.domain.modelo.RegistroRequest
import com.example.hospitalcompose.domain.modelo.TokenResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuariosService {
    @POST("api/auth/registro")
    suspend fun registerUser(@Body usuario: RegistroRequest): Response<ResponseBody>

    @POST("api/auth/login")
    suspend fun loginUser(@Body usuario: LoginRequest): Response<TokenResponse>
}

