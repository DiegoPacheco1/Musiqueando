
package com.example.hospitalcompose.data.utils

import com.example.hospitalcompose.data.remote.apiServices.AuthApiService
import com.example.hospitalcompose.domain.modelo.TokenResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import retrofit2.Response
import javax.inject.Inject
import dagger.Lazy
import okhttp3.logging.HttpLoggingInterceptor

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val service: Lazy<AuthApiService>,
) : Authenticator {

    override fun authenticate(route: Route?, response: okhttp3.Response): Request? {
        val refreshToken = runBlocking {
            tokenManager.getRefreshToken().first()
        }

        return runBlocking {
            val newTokenResponse = getNewToken(refreshToken)

            if (!newTokenResponse.isSuccessful || newTokenResponse.body() == null) {
                tokenManager.deleteTokens()
                return@runBlocking null
            }

            newTokenResponse.body()?.let {
                tokenManager.saveTokens(it.accessToken, it.refreshToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): Response<TokenResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return service.get().refreshToken("Bearer $refreshToken")
    }

}
