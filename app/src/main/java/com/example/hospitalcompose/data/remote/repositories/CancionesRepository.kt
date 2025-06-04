package com.example.hospitalcompose.data.remote.repositories

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.datasource.CancionesRemoteDataSource
import com.example.hospitalcompose.domain.modelo.Cancion
import javax.inject.Inject

/**
 * Adaptación de tu repositorio para usar los nuevos métodos de RemoteDataSource.
 */
class CancionesRepository @Inject constructor(
    private val remote: CancionesRemoteDataSource
) {

    /** Devuelve todas las canciones */
    suspend fun obtenerTodasLasCanciones(): NetworkResult<List<Cancion>> =
        remote.getAll()

    /** Detalle de una canción por ID */
    suspend fun obtenerDetalleCancion(id: String): NetworkResult<Cancion> =
        remote.getById(id)

    /** Busca canciones por artistaId */
    suspend fun buscarCancionesPorArtista(artistaId: String): NetworkResult<List<Cancion>> =
        remote.getByArtista(artistaId)

    /** Inserta una nueva canción */
    suspend fun insertarCancion(cancion: Cancion): NetworkResult<Cancion> =
        remote.create(cancion)

    /** Elimina una canción por ID */
    suspend fun eliminarCancion(id: String): NetworkResult<Unit> =
        remote.delete(id)
}
