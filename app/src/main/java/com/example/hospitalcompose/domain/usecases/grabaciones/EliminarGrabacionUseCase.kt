package com.example.hospitalcompose.domain.usecases.grabaciones

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.GrabacionesRepository
import javax.inject.Inject

class EliminarGrabacionUseCase @Inject constructor(
    private val repo: GrabacionesRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<Unit> =
        repo.eliminarGrabacion(id)
}
