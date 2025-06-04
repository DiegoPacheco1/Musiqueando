package com.example.hospitalcompose.domain.modelo

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Grabacion(
    @SerialName("_id")
    val id: String,
    val usuarioId: String,
    val nombre: String,
    val fecha: String,
    val duracion: Double,
    val path: String
)
