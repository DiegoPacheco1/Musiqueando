package com.example.hospitalcompose.domain.usecases.canciones

import com.example.hospitalcompose.data.remote.repositories.CancionesRepository
import com.example.hospitalcompose.data.remote.NetworkResult
import javax.inject.Inject

class EliminarCancionUseCase @Inject constructor(
    private val cancionesRepository: CancionesRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<Unit> =
        cancionesRepository.eliminarCancion(id)
}