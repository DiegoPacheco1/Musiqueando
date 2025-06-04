package com.example.hospitalcompose.domain.usecases.artistas

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.ArtistasRepository
import javax.inject.Inject

class EliminarArtistaUseCase @Inject constructor(
    private val repo: ArtistasRepository
) {
    suspend operator fun invoke(id: String): NetworkResult<Unit> =
        repo.eliminarArtista(id)
}
