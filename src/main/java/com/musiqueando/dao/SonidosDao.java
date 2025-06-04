package com.musiqueando.dao;

import com.musiqueando.dao.interfaces.SonidoRepository;
import com.musiqueando.domain.Sonido;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SonidosDao {

    private final SonidoRepository sonidoRepo;

    public SonidosDao(SonidoRepository sonidoRepo) {
        this.sonidoRepo = sonidoRepo;
    }

    public List<Sonido> findAll() {
        return sonidoRepo.findAll();
    }

    public Optional<Sonido> findById(String id) {
        return sonidoRepo.findById(id);
    }

    public List<Sonido> findByCategoria(String categoria) {
        return sonidoRepo.findByCategoria(categoria);
    }

    public Sonido save(Sonido sonido) {
        return sonidoRepo.save(sonido);
    }

    public void deleteById(String id) {
        sonidoRepo.deleteById(id);
    }
}
