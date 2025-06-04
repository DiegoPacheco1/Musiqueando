package com.musiqueando.errors.excepciones;

public class ArtistaNoEncontradoException extends RuntimeException {
    public ArtistaNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
