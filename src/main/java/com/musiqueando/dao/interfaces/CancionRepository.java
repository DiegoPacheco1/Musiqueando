
package com.musiqueando.dao.interfaces;

import com.musiqueando.domain.Cancion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepository extends MongoRepository<Cancion, String> {
    List<Cancion> findByArtistaId(ObjectId artistaId);
}
