package com.musiqueando.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.musiqueando.dao.interfaces.ArtistaRepository;
import com.musiqueando.domain.Artista;

@Repository
public class ArtistasDao {

    private final ArtistaRepository artistaRepo;

    public ArtistasDao(ArtistaRepository artistaRepo) {
        this.artistaRepo = artistaRepo;
    }

    public List<Artista> findAll() {
        return artistaRepo.findAll();
    }

    public Optional<Artista> findById(String id) {
        return artistaRepo.findById(id);
    }

    public Artista save(Artista artista) {
        return artistaRepo.save(artista);
    }

    public void deleteById(String id) {
        artistaRepo.deleteById(id);
    }
}
