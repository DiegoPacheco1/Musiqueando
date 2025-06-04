package com.example.hospitalcompose.domain.usecases.grabaciones

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.GrabacionesRepository
import com.example.hospitalcompose.domain.modelo.Grabacion
import javax.inject.Inject

class ObtenerDetalleGrabacionUseCase @Inject constructor(
    private val repo: GrabacionesRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<Grabacion> =
        repo.obtenerDetalleGrabacion(id)
}
