package com.example.hospitalcompose.data.local

import com.example.hospitalcompose.data.local.RitmoDao
import com.example.hospitalcompose.domain.modelo.RitmoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RitmosRepository @Inject constructor(
    private val dao: RitmoDao
) {
    fun obtenerTodos(): Flow<List<RitmoEntity>> =
        dao.getAll()

    suspend fun insertarTodos(items: List<RitmoEntity>) =
        dao.insertAll(items)
}
