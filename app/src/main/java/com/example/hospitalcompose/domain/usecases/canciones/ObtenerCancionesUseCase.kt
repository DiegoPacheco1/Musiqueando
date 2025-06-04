package com.example.hospitalcompose.domain.usecases.canciones

import com.example.hospitalcompose.data.remote.repositories.CancionesRepository
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.domain.modelo.Cancion
import javax.inject.Inject

class ObtenerCancionesUseCase @Inject constructor(
    private val cancionesRepository: CancionesRepository
) {
    suspend operator fun invoke(): NetworkResult<List<Cancion>> =
        cancionesRepository.obtenerTodasLasCanciones()
}