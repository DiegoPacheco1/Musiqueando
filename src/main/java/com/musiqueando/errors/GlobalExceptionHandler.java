package com.musiqueando.errors;

import com.musiqueando.errors.excepciones.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CancionesNoEncontradasException.class)
    public ResponseEntity<ApiError> handleCancionesNoEncontradas(CancionesNoEncontradasException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(CancionNoEncontradaException.class)
    public ResponseEntity<ApiError> handleCancionNoEncontrada(CancionNoEncontradaException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ApiError> handleUsuarioNoEncontrado(UsuarioNoEncontradoException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(UsuarioYaExisteException.class)
    public ResponseEntity<ApiError> handleUsuarioYaExiste(UsuarioYaExisteException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(ArtistaNoEncontradoException.class)
    public ResponseEntity<ApiError> handleArtistaNoEncontrado(ArtistaNoEncontradoException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ArtistasNoEncontradosException.class)
    public ResponseEntity<ApiError> handleArtistasNoEncontrados(ArtistasNoEncontradosException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(GrabacionNoEncontradaException.class)
    public ResponseEntity<ApiError> handleGrabacionNoEncontrada(GrabacionNoEncontradaException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(GrabacionesNoEncontradasException.class)
    public ResponseEntity<ApiError> handleGrabacionesNoEncontradas(GrabacionesNoEncontradasException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(SonidoNoEncontradoException.class)
    public ResponseEntity<ApiError> handleSonidoNoEncontrado(SonidoNoEncontradoException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(SonidosNoEncontradosException.class)
    public ResponseEntity<ApiError> handleSonidosNoEncontrados(SonidosNoEncontradosException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
