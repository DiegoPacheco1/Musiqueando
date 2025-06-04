package com.musiqueando.dao;

import com.musiqueando.dao.interfaces.UsuarioRepository;
import com.musiqueando.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuariosDao {

    private final UsuarioRepository repository;

    public UsuariosDao(UsuarioRepository repository) {
        this.repository = repository;
    }


    public Optional<Usuario> findById(String usuario) {
        return repository.findById(usuario);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }


    public Usuario findByCredenciales(String usuario, String password) {
        return repository.findByUsuarioAndPassword(usuario, password);
    }
}
