package com.example.hospitalcompose.data.remote.repositories

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.datasource.SonidosRemoteDataSource
import com.example.hospitalcompose.domain.modelo.Sonido
import javax.inject.Inject

class SonidosRepository @Inject constructor(
    private val remote: SonidosRemoteDataSource
) {

    suspend fun obtenerTodosLosSonidos(): NetworkResult<List<Sonido>> =
        remote.getAll()

    suspend fun obtenerDetalleSonido(id: String): NetworkResult<Sonido> =
        remote.getById(id)

    suspend fun buscarSonidosPorCategoria(categoria: String): NetworkResult<List<Sonido>> =
        remote.getByCategoria(categoria)

    suspend fun insertarSonido(sonido: Sonido): NetworkResult<Sonido> =
        remote.add(sonido)

    suspend fun actualizarSonido(id: String, sonido: Sonido): NetworkResult<Sonido> =
        remote.update(id, sonido)

    suspend fun eliminarSonido(id: String): NetworkResult<Unit> =
        remote.delete(id)
}
