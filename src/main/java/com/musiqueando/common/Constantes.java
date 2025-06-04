package com.musiqueando.common;

public class Constantes {

    // Mensajes
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_NO_PROPORCIONADO = "Token no proporcionado o formato incorrecto";
    public static final String TOKEN_INVALIDO = "Token no proporcionado o inválido";
    public static final String ERROR_VALIDAR_TOKEN = "Error al validar el token: ";
    public static final String TOKEN_INVALIDO_O_EXPIRADO = "Token inválido o expirado";
    public static final String USUARIO_AUTENTICADO = "Usuario autenticado: {}";
    public static final String BEARER_ = "Bearer ";

    // Rutas
    public static final String LOGIN_URI = "/api/auth/login";
    public static final String REGISTRO_URI = "/api/auth/registro";
    public static final String ACTIVAR_URI = "/api/auth/activar";




    // Mensajes
    public static final String ERROR_LOGIN = "Error al loguearse";

    // JWT
    public static final String JWT_SUBJECT = "Cliente";
    public static final String JWT_ISSUER = "Servidor";

    // Rutas
    public static final String API_PELICULAS = "/api/peliculas";
    public static final String API_PELICULAS_ID = "/{id}";

    // Mensajes de Estado
    public static final String PELICULA_NO_ENCONTRADA = "Película no encontrada";
    // Rutas
    public static final String API_AUTH = "/api/auth";
    public static final String REGISTRO = "/registro";
    public static final String ACTIVAR = "/activar";

    // Mensajes
    public static final String REGISTRO_EXITOSO = "Registro exitoso. Verifique su correo electrónico.";
    public static final String ERROR_REGISTRO = "Error al registrar usuario: ";
    public static final String ACTIVACION_EXITOSA = "Tu cuenta ha sido activada con éxito.";
    public static final String CODIGO_INVALIDO = "El código de activación no es válido.";



}
