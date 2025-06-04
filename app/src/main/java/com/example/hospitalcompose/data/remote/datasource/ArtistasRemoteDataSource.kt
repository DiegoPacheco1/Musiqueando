package com.example.hospitalcompose.data.remote.datasource

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.apiServices.ArtistasService
import com.example.hospitalcompose.data.remote.datasource.utils.BaseApiResponse
import com.example.hospitalcompose.domain.modelo.Artista
import javax.inject.Inject

class ArtistasRemoteDataSource @Inject constructor(
    private val service: ArtistasService
) : BaseApiResponse() {

    suspend fun getAll(): NetworkResult<List<Artista>> =
        safeApiCall { service.getAll() }

    suspend fun getById(id: String): NetworkResult<Artista> =
        safeApiCall { service.getById(id) }

    suspend fun add(artista: Artista): NetworkResult<Artista> =
        safeApiCall { service.add(artista) }

    suspend fun update(id: String, artista: Artista): NetworkResult<Artista> =
        safeApiCall { service.update(id, artista) }

    suspend fun delete(id: String): NetworkResult<Unit> =
        safeApiCall { service.delete(id) }
}
