package com.example.hospitalcompose.domain.modelo

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Sonido(
    @SerialName("_id")
    val id: String,
    val nombre: String,
    val categoria: String,
    val duracion: Double,
    val path: String
)
