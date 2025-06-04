package com.musiqueando.errors.excepciones;

public class CancionesNoEncontradasException extends RuntimeException {
    public CancionesNoEncontradasException(String mensaje) {
        super(mensaje);
    }
}