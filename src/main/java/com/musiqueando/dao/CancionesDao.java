package com.musiqueando.dao;

import com.musiqueando.dao.interfaces.CancionRepository;
import com.musiqueando.domain.Cancion;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CancionesDao {
    private final CancionRepository repo;

    public CancionesDao(CancionRepository repo) {
        this.repo = repo;
    }

    public List<Cancion> findAll() {
        return repo.findAll();
    }

    public Optional<Cancion> findById(String id) {
        return repo.findById(id);
    }

    public Cancion save(Cancion c) {
        return repo.save(c);
    }

    public void deleteById(String id) {
        repo.deleteById(id);
    }

    public List<Cancion> findByArtistaId(ObjectId artistaId) {
        return repo.findByArtistaId(artistaId);
    }
}
