package com.musiqueando.errors.excepciones;

public class ArtistasNoEncontradosException extends RuntimeException {
    public ArtistasNoEncontradosException(String mensaje) {
        super(mensaje);
    }
}
