package com.example.hospitalcompose.domain.modelo

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Artista(
    @SerialName("_id")
    val id: String,
    val nombre: String
)
