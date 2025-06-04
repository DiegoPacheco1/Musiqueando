package com.example.hospitalcompose.data.remote.repositories

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.datasource.ArtistasRemoteDataSource
import com.example.hospitalcompose.domain.modelo.Artista
import javax.inject.Inject

class ArtistasRepository @Inject constructor(
    private val remote: ArtistasRemoteDataSource
) {

    suspend fun obtenerTodosLosArtistas(): NetworkResult<List<Artista>> =
        remote.getAll()

    suspend fun obtenerDetalleArtista(id: String): NetworkResult<Artista> =
        remote.getById(id)

    suspend fun insertarArtista(artista: Artista): NetworkResult<Artista> =
        remote.add(artista)

    suspend fun actualizarArtista(id: String, artista: Artista): NetworkResult<Artista> =
        remote.update(id, artista)

    suspend fun eliminarArtista(id: String): NetworkResult<Unit> =
        remote.delete(id)
}
