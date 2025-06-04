package com.example.hospitalcompose.domain.usecases.artistas

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.ArtistasRepository
import com.example.hospitalcompose.domain.modelo.Artista
import javax.inject.Inject

class ObtenerArtistasUseCase @Inject constructor(
    private val repo: ArtistasRepository
) {
    suspend operator fun invoke(): NetworkResult<List<Artista>> =
        repo.obtenerTodosLosArtistas()
}
