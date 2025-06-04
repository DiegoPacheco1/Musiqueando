package com.example.hospitalcompose.domain.modelo

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val nombre: String,
    val password: String
)
