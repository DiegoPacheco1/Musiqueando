package com.example.hospitalcompose.domain.usecases.sonidos

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.SonidosRepository
import com.example.hospitalcompose.domain.modelo.Sonido
import javax.inject.Inject

class ObtenerDetalleSonidoUseCase @Inject constructor(
    private val repo: SonidosRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<Sonido> =
        repo.obtenerDetalleSonido(id)
}
