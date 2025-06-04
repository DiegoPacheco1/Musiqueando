package com.example.hospitalcompose.data.remote.apiServices


import com.example.hospitalcompose.domain.modelo.Sonido
import retrofit2.Response
import retrofit2.http.*

interface SonidosService {

    /** Lista todas las grabaciones */
    @GET("api/sonidos")
    suspend fun getAll(): Response<List<Sonido>>

    /** Obtiene un sonido por ID */
    @GET("api/sonidos/{id}")
    suspend fun getById(@Path("id") id: String): Response<Sonido>

    /** Obtiene sonidos por categoría */
    @GET("api/sonidos/categoria/{categoria}")
    suspend fun getByCategoria(@Path("categoria") categoria: String): Response<List<Sonido>>

    /** Crea un nuevo sonido */
    @POST("api/sonidos")
    suspend fun add(@Body sonido: Sonido): Response<Sonido>

    /** Actualiza un sonido existente */
    @PUT("api/sonidos/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body sonido: Sonido
    ): Response<Sonido>

    /** Elimina un sonido por ID */
    @DELETE("api/sonidos/{id}")
    suspend fun delete(@Path("id") id: String): Response<Unit>
}
