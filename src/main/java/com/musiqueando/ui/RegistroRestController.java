package com.musiqueando.ui;

import com.musiqueando.domain.RegistroRequest;
import com.musiqueando.domain.Usuario;
import com.musiqueando.service.UsuariosService;
import com.musiqueando.ui.common.ConstantesUI;
import com.musiqueando.errors.excepciones.UsuarioYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantesUI.API_AUTH)
public class RegistroRestController {

    private final UsuariosService usuariosService;

    public RegistroRestController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping(ConstantesUI.API_REGISTRO)
    public ResponseEntity<String> registro(@RequestBody RegistroRequest request) {
        try {
            Usuario nuevo = new Usuario(request.getUsuario(), request.getPassword());
            usuariosService.add(nuevo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ConstantesUI.REGISTRO_EXITOSO);
        } catch (UsuarioYaExisteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ConstantesUI.ERROR_USUARIO_YA_EXISTE);
        }
    }
}

