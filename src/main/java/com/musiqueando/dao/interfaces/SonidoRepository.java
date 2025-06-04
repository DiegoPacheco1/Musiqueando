package com.musiqueando.dao.interfaces;

import com.musiqueando.domain.Sonido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SonidoRepository extends MongoRepository<Sonido, String> {
    List<Sonido> findByCategoria(String categoria);
}
