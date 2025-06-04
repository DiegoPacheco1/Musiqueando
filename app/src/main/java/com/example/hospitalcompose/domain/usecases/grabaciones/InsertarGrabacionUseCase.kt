package com.example.hospitalcompose.domain.usecases.grabaciones

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.GrabacionesRepository
import com.example.hospitalcompose.domain.modelo.Grabacion
import javax.inject.Inject

class InsertarGrabacionUseCase @Inject constructor(
    private val repo: GrabacionesRepository
) {
    suspend operator fun invoke(grabacion: Grabacion): NetworkResult<Grabacion> =
        repo.insertarGrabacion(grabacion)
}
