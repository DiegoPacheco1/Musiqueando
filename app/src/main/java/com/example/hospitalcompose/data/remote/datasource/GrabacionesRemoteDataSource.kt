package com.example.hospitalcompose.data.remote.datasource

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.apiServices.GrabacionesService
import com.example.hospitalcompose.data.remote.datasource.utils.BaseApiResponse
import com.example.hospitalcompose.domain.modelo.Grabacion
import javax.inject.Inject

class GrabacionesRemoteDataSource @Inject constructor(
    private val service: GrabacionesService
) : BaseApiResponse() {

    /** Lista todas las grabaciones */
    suspend fun getAll(): NetworkResult<List<Grabacion>> =
        safeApiCall { service.getAll() }

    /** Obtiene una grabación por ID */
    suspend fun getById(id: String): NetworkResult<Grabacion> =
        safeApiCall { service.getById(id) }

    /** Crea una nueva grabación */
    suspend fun create(grabacion: Grabacion): NetworkResult<Grabacion> =
        safeApiCall { service.create(grabacion) }

    /** Elimina una grabación por ID */
    suspend fun delete(id: String): NetworkResult<Unit> =
        safeApiCall { service.delete(id) }
}
