package com.musiqueando.dao.interfaces;

import com.musiqueando.domain.Grabacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GrabacionRepository extends MongoRepository<Grabacion, String> {
}
