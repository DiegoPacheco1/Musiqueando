package com.musiqueando.dao.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.musiqueando.domain.Artista;

public interface ArtistaRepository extends MongoRepository<Artista, String> {
}
