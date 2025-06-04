package com.example.hospitalcompose.domain.usecases.canciones

import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.data.remote.repositories.CancionesRepository
import com.example.hospitalcompose.domain.modelo.Cancion
import javax.inject.Inject

class BuscarCancionesPorArtistaUseCase @Inject constructor(
    private val cancionesRepository: CancionesRepository
) {
    suspend operator fun invoke(artista: String): NetworkResult<List<Cancion>> =
        cancionesRepository.buscarCancionesPorArtista(artista)
}
