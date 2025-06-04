package com.example.hospitalcompose.domain.modelo

import kotlinx.serialization.Serializable

@Serializable
data class RegistroRequest(
    val usuario: String,
    val password: String
)
