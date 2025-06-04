package com.musiqueando.filters;

import com.musiqueando.common.Constantes;
import com.musiqueando.components.TokenComponent;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final TokenComponent tokenComponent;
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        String authHeader = request.getHeader(Constantes.AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(Constantes.BEARER_)) {
            logger.warn(Constantes.TOKEN_NO_PROPORCIONADO);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constantes.TOKEN_INVALIDO);
            return;
        }

        String token = authHeader.substring(7);

        if (!tokenComponent.validate(token)) {
            logger.error(Constantes.ERROR_VALIDAR_TOKEN);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constantes.TOKEN_INVALIDO_O_EXPIRADO);
            return;
        }

        try {
            String user = tokenComponent.getUsername(token);
            logger.info(Constantes.USUARIO_AUTENTICADO, user);

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error(Constantes.ERROR_VALIDAR_TOKEN + "{}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constantes.TOKEN_INVALIDO_O_EXPIRADO);
        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Constantes.LOGIN_URI.equals(request.getRequestURI()) ||
                Constantes.REGISTRO_URI.equals(request.getRequestURI()) ||
                Constantes.ACTIVAR_URI.equals(request.getRequestURI());
    }
}

