package com.example.hospitalcompose.domain.modelo

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Cancion(
    @SerialName("_id")
    val id: String,
    val nombre: String,
    val artista: String,
    val genero: String,
    val cejilla: Int,
    val acordes: List<String>,
    val letra: List<String>
)
