package com.musiqueando.service.common;

public class ConstantesService {
    public static final String CONFIRMACIÓN_DE_REGISTRO = "Confirmación de registro";
    public static final String HOLA = "Hola ";
    public static final String SIGUIENTE_ENLACE = ",\n\nPor favor, activa tu cuenta haciendo clic en el siguiente enlace:\n";
    public static final String HTTP_LOCALHOST_8080_ACTIVAR_CODIGO = "http://localhost:8080/activar?codigo=";

    // Mensajes de error para directores
    public static final String DIRECTORES_NO_ENCONTRADOS = "No se han encontrado directores en la base de datos.";
    public static final String DIRECTOR_NO_ENCONTRADO = "No se encontró ningún director con ID ";
    public static final String DIRECTOR_NO_ELIMINADO = "No se pudo eliminar el director con ID ";

    // Mensajes de error para películas
    public static final String PELICULAS_NO_ENCONTRADAS = "No se han recibido las películas.";
    public static final String PELICULA_NO_ENCONTRADA = "No se encontró ninguna película con ID ";
    public static final String PELICULA_NO_ELIMINADA = "No se pudo eliminar la película con ID ";

    // Mensajes de error para usuarios
    public static final String USUARIO_YA_EXISTE = "El usuario con correo %s o nombre %s ya está registrado.";
    public static final String CODIGO_ACTIVACION_INVALIDO = "El código de activación proporcionado no es válido.";
    public static final String USUARIO_NO_ENCONTRADO = "Usuario o contraseña incorrectos.";

    public static final String CANCIONES_NO_ENCONTRADAS            = "No se encontraron canciones.";
    public static final String CANCION_NO_ENCONTRADA             = "Canción no encontrada con id: ";
    public static final String CANCION_NO_ELIMINADA              = "No se pudo eliminar la canción con id: ";
    public static final String CANCIONES_NO_ENCONTRADAS_POR_ARTISTA = "No se encontraron canciones para el artista: ";
    public static final String CANCIONES_NO_ENCONTRADAS_POR_GENERO  = "No se encontraron canciones para el género: ";


    // Constantes para usuarios
    public static final String USUARIOS_NO_ENCONTRADOS               = "No se encontraron usuarios.";
    public static final String USUARIO_NO_ENCONTRADO_ID              = "No existe el usuario: ";

    public static final String ARTISTAS_NO_ENCONTRADOS = "No se encontraron artistas.";
    public static final String ARTISTA_NO_ENCONTRADO = "Artista no encontrado: %s";
    public static final String GRABACIONES_NO_ENCONTRADAS = "No se encontraron grabaciones.";
    public static final String GRABACION_NO_ENCONTRADA = "Grabación no encontrada: %s";
    public static final String GRABACIONES_NO_ENCONTRADAS_POR_USUARIO = "No se encontraron grabaciones para el usuario: ";

    public static final String SONIDOS_NO_ENCONTRADOS = "No se encontraron sonidos.";
    public static final String SONIDO_NO_ENCONTRADO = "Sonido no encontrado: %s";
    public static final String SONIDOS_NO_ENCONTRADOS_POR_CATEGORIA = "No se encontraron sonidos para la categoría: ";


}
