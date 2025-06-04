// src/main/java/com/example/musiqueando/data/remote/service/CancionesApiService.kt
package com.example.hospitalcompose.data.remote.apiServices


import com.example.hospitalcompose.domain.modelo.Cancion
import retrofit2.Response
import retrofit2.http.*

interface CancionesService {

    /** Lista todas las canciones */
    @GET("/api/canciones")
    suspend fun getAll(): Response<List<Cancion>>

    /** Obtiene una canción por ID */
    @GET("/api/canciones/{id}")
    suspend fun getById(@Path("id") id: String): Response<Cancion>

    /** Obtiene canciones por artistaId */
    @GET("/api/canciones/artista/{artistaId}")
    suspend fun getByArtista(@Path("artistaId") artistaId: String): Response<List<Cancion>>

    /** Crea una nueva canción */
    @POST("/api/canciones")
    suspend fun create(@Body cancion: Cancion): Response<Cancion>

    /** Elimina una canción por ID */
    @DELETE("/api/canciones/{id}")
    suspend fun delete(@Path("id") id: String): Response<Unit>
}
