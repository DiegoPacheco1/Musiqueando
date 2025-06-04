package com.example.hospitalcompose.data.remote.datasource

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.apiServices.SonidosService
import com.example.hospitalcompose.data.remote.datasource.utils.BaseApiResponse
import com.example.hospitalcompose.domain.modelo.Sonido
import javax.inject.Inject

class SonidosRemoteDataSource @Inject constructor(
    private val service: SonidosService
) : BaseApiResponse() {

    suspend fun getAll(): NetworkResult<List<Sonido>> =
        safeApiCall { service.getAll() }

    suspend fun getById(id: String): NetworkResult<Sonido> =
        safeApiCall { service.getById(id) }

    suspend fun getByCategoria(categoria: String): NetworkResult<List<Sonido>> =
        safeApiCall { service.getByCategoria(categoria) }

    suspend fun add(sonido: Sonido): NetworkResult<Sonido> =
        safeApiCall { service.add(sonido) }

    suspend fun update(id: String, sonido: Sonido): NetworkResult<Sonido> =
        safeApiCall { service.update(id, sonido) }

    suspend fun delete(id: String): NetworkResult<Unit> =
        safeApiCall { service.delete(id) }
}
