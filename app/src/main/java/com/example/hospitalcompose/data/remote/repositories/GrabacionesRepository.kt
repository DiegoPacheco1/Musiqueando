package com.example.hospitalcompose.data.remote.repositories

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.datasource.GrabacionesRemoteDataSource
import com.example.hospitalcompose.domain.modelo.Grabacion
import javax.inject.Inject

class GrabacionesRepository @Inject constructor(
    private val remote: GrabacionesRemoteDataSource
) {

    suspend fun obtenerTodasLasGrabaciones(): NetworkResult<List<Grabacion>> =
        remote.getAll()

    suspend fun obtenerDetalleGrabacion(id: String): NetworkResult<Grabacion> =
        remote.getById(id)

    suspend fun insertarGrabacion(grabacion: Grabacion): NetworkResult<Grabacion> =
        remote.create(grabacion)

    suspend fun eliminarGrabacion(id: String): NetworkResult<Unit> =
        remote.delete(id)
}
