package com.musiqueando.dao.interfaces;

import com.musiqueando.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByUsuarioAndPassword(String usuario, String password);
}
