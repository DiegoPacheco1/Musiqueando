package com.musiqueando.dao;

import com.musiqueando.dao.interfaces.GrabacionRepository;
import com.musiqueando.domain.Grabacion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GrabacionesDao {

    private final GrabacionRepository grabacionRepo;

    public GrabacionesDao(GrabacionRepository grabacionRepo) {
        this.grabacionRepo = grabacionRepo;
    }

    public List<Grabacion> findAll() {
        return grabacionRepo.findAll();
    }

    public Optional<Grabacion> findById(String id) {
        return grabacionRepo.findById(id);
    }

    public Grabacion save(Grabacion grabacion) {
        return grabacionRepo.save(grabacion);
    }

    public void deleteById(String id) {
        grabacionRepo.deleteById(id);
    }
}
