// src/main/java/com/example/hospitalcompose/data/remote/datasource/CancionesRemoteDataSource.kt
package com.example.hospitalcompose.data.remote.datasource

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.apiServices.CancionesService
import com.example.hospitalcompose.data.remote.datasource.utils.BaseApiResponse
import com.example.hospitalcompose.domain.modelo.Cancion
import javax.inject.Inject

/**
 * Llama a los endpoints de CancionesService usando los nombres de método
 * que coinciden con tu CancionesRestController.
 */
class CancionesRemoteDataSource @Inject constructor(
    private val cancionesService: CancionesService
) : BaseApiResponse() {

    /** Lista todas las canciones */
    suspend fun getAll(): NetworkResult<List<Cancion>> =
        safeApiCall { cancionesService.getAll() }

    /** Obtiene una canción por su ID */
    suspend fun getById(id: String): NetworkResult<Cancion> =
        safeApiCall { cancionesService.getById(id) }

    /** Obtiene canciones filtrando por artistaId */
    suspend fun getByArtista(artistaId: String): NetworkResult<List<Cancion>> =
        safeApiCall { cancionesService.getByArtista(artistaId) }

    /** Crea una nueva canción */
    suspend fun create(cancion: Cancion): NetworkResult<Cancion> =
        safeApiCall { cancionesService.create(cancion) }

    /** Elimina una canción por ID */
    suspend fun delete(id: String): NetworkResult<Unit> =
        safeApiCall { cancionesService.delete(id) }
}
