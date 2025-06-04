
package com.example.hospitalcompose.data.remote.apiServices

import com.example.hospitalcompose.domain.modelo.TokenResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/auth/refresh")
    suspend fun refreshToken(
        @Header("Authorization") token: String
    ): Response<TokenResponse>
}

