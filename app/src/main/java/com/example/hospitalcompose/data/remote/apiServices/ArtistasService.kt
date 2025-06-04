package com.example.hospitalcompose.data.remote.apiServices


import com.example.hospitalcompose.domain.modelo.Artista
import retrofit2.Response
import retrofit2.http.*

interface ArtistasService {

    /** Lista todos los artistas */
    @GET("api/artistas")
    suspend fun getAll(): Response<List<Artista>>

    /** Obtiene un artista por ID */
    @GET("api/artistas/{id}")
    suspend fun getById(@Path("id") id: String): Response<Artista>

    /** Crea un nuevo artista */
    @POST("api/artistas")
    suspend fun add(@Body artista: Artista): Response<Artista>

    /** Actualiza un artista existente */
    @PUT("api/artistas/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body artista: Artista
    ): Response<Artista>

    /** Elimina un artista por ID */
    @DELETE("api/artistas/{id}")
    suspend fun delete(@Path("id") id: String): Response<Unit>
}
