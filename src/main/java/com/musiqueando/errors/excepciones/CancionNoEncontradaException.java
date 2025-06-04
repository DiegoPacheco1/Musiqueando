package com.musiqueando.errors.excepciones;

public class CancionNoEncontradaException extends RuntimeException {
    public CancionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}