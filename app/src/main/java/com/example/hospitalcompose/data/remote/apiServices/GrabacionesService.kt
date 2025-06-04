package com.example.hospitalcompose.data.remote.apiServices

import com.example.hospitalcompose.domain.modelo.Grabacion
import retrofit2.Response
import retrofit2.http.*

interface GrabacionesService {

    /** Lista todas las grabaciones */
    @GET("api/grabaciones")
    suspend fun getAll(): Response<List<Grabacion>>

    /** Obtiene una grabación por ID */
    @GET("api/grabaciones/{id}")
    suspend fun getById(@Path("id") id: String): Response<Grabacion>

    /** Crea una nueva grabación */
    @POST("api/grabaciones")
    suspend fun create(@Body grabacion: Grabacion): Response<Grabacion>

    /** Elimina una grabación por ID */
    @DELETE("api/grabaciones/{id}")
    suspend fun delete(@Path("id") id: String): Response<Unit>
}
