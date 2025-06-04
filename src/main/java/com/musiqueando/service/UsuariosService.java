package com.musiqueando.service;

import com.musiqueando.dao.UsuariosDao;
import com.musiqueando.domain.Usuario;
import com.musiqueando.errors.excepciones.UsuarioNoEncontradoException;
import com.musiqueando.errors.excepciones.UsuarioYaExisteException;
import com.musiqueando.service.common.ConstantesService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    private final UsuariosDao usuariosDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuariosService(UsuariosDao usuariosDao) {
        this.usuariosDao = usuariosDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario add(Usuario usuario) {
        if (usuariosDao.findById(usuario.getUsuario()).isPresent()) {
            throw new UsuarioYaExisteException(
                    String.format(ConstantesService.USUARIO_YA_EXISTE, usuario.getUsuario())
            );
        }

        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        return usuariosDao.save(usuario);
    }

    public void validate(String usuario, String password) {
        Usuario u = usuariosDao.findById(usuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException(ConstantesService.USUARIO_NO_ENCONTRADO));

        if (!passwordEncoder.matches(password, u.getPassword())) {
            throw new UsuarioNoEncontradoException(ConstantesService.USUARIO_NO_ENCONTRADO);
        }
    }
}
