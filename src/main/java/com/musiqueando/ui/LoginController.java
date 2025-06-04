package com.musiqueando.ui;

import com.musiqueando.service.UsuariosService;
import com.musiqueando.ui.common.ConstantesUI;
import com.musiqueando.components.TokenComponent;
import com.musiqueando.domain.LoginRequest;
import com.musiqueando.domain.RefreshTokenRequest;
import com.musiqueando.filters.AuthenticationResponse;
import com.musiqueando.errors.excepciones.UsuarioNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ConstantesUI.API_AUTH)
public class LoginController {

    private final UsuariosService usuariosService;
    private final TokenComponent tokenComponent;

    public LoginController(UsuariosService usuariosService, TokenComponent tokenComponent) {
        this.usuariosService = usuariosService;
        this.tokenComponent = tokenComponent;
    }

    @PostMapping(ConstantesUI.API_LOGIN)
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        try {

            usuariosService.validate(loginRequest.getNombre(), loginRequest.getPassword());
        } catch (UsuarioNoEncontradoException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    ConstantesUI.ERROR_USUARIO_CONTRASEÑA_INVALIDOS
            );
        }

        String username = loginRequest.getNombre();
        String accessToken  = tokenComponent.generarToken(username, 1200000);
        String refreshToken = tokenComponent.generarToken(username, 3600000);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @PostMapping(ConstantesUI.API_REFRESH)
    public AuthenticationResponse refresh(@RequestBody RefreshTokenRequest refreshRequest) {
        String refreshToken = refreshRequest.getRefreshToken();

        if (!tokenComponent.validate(refreshToken)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    ConstantesUI.ERROR_REFRESH_TOKEN_INVALIDO
            );
        }

        String username       = tokenComponent.getUsername(refreshToken);
        String newAccessToken = tokenComponent.generarToken(username, 1200000);

        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .build();
    }
}

