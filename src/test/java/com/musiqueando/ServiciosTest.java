package com.musiqueando;

import com.musiqueando.dao.ArtistasDao;
import com.musiqueando.dao.CancionesDao;
import com.musiqueando.dao.GrabacionesDao;
import com.musiqueando.dao.SonidosDao;
import com.musiqueando.dao.UsuariosDao;
import com.musiqueando.domain.Artista;
import com.musiqueando.domain.Cancion;
import com.musiqueando.domain.Grabacion;
import com.musiqueando.domain.Sonido;
import com.musiqueando.domain.Usuario;
import com.musiqueando.errors.excepciones.ArtistaNoEncontradoException;
import com.musiqueando.errors.excepciones.CancionNoEncontradaException;
import com.musiqueando.errors.excepciones.CancionesNoEncontradasException;
import com.musiqueando.errors.excepciones.GrabacionNoEncontradaException;
import com.musiqueando.errors.excepciones.GrabacionesNoEncontradasException;
import com.musiqueando.errors.excepciones.SonidoNoEncontradoException;
import com.musiqueando.errors.excepciones.SonidosNoEncontradosException;
import com.musiqueando.errors.excepciones.UsuarioNoEncontradoException;
import com.musiqueando.errors.excepciones.UsuarioYaExisteException;
import com.musiqueando.service.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiciosTest {

    @Mock
    private ArtistasDao artistasDao;
    @Mock
    private CancionesDao cancionesDao;
    @Mock
    private GrabacionesDao grabacionesDao;
    @Mock
    private SonidosDao sonidosDao;
    @Mock
    private UsuariosDao usuariosDao;

    @InjectMocks
    private ArtistasService artistasService;
    @InjectMocks
    private CancionesService cancionesService;
    @InjectMocks
    private GrabacionesService grabacionesService;
    @InjectMocks
    private SonidosService sonidosService;
    @InjectMocks
    private UsuariosService usuariosService;

    @Test
    void obtenerTodosArtistas_deberíaDevolverLista() {
        Artista a1 = new Artista("1", "Alice");
        Artista a2 = new Artista("2", "Bob");
        when(artistasDao.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Artista> resultado = artistasService.getAll();

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(a1));
        assertTrue(resultado.contains(a2));
    }

    @Test
    void obtenerTodosArtistas_deberíaLanzarExcepción_siVacio() {
        when(artistasDao.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ArtistaNoEncontradoException.class, () -> artistasService.getAll());
    }

    @Test
    void obtenerTodosArtistas_deberíaLanzarExcepción_siNull() {
        when(artistasDao.findAll()).thenReturn(null);

        assertThrows(ArtistaNoEncontradoException.class, () -> artistasService.getAll());
    }

    @Test
    void obtenerArtistaPorId_deberíaDevolverArtista_siExiste() {
        Artista a = new Artista("1", "Charlie");
        when(artistasDao.findById("1")).thenReturn(Optional.of(a));

        Artista resultado = artistasService.getById("1");

        assertSame(a, resultado);
    }

    @Test
    void obtenerArtistaPorId_deberíaLanzarExcepción_siNoExiste() {
        when(artistasDao.findById("X")).thenReturn(Optional.empty());

        assertThrows(ArtistaNoEncontradoException.class, () -> artistasService.getById("X"));
    }

    @Test
    void agregarArtista_deberíaGuardarYDevolver() {
        Artista entrada = new Artista(null, "Dave");
        Artista guardado = new Artista("42", "Dave");
        when(artistasDao.save(entrada)).thenReturn(guardado);

        Artista resultado = artistasService.add(entrada);

        assertEquals("42", resultado.getId());
        assertEquals("Dave", resultado.getNombre());
        verify(artistasDao).save(entrada);
    }

    @Test
    void actualizarArtista_deberíaActualizarYDevolver_siExiste() {
        Artista entrada = new Artista(null, "Eve");
        Artista existente = new Artista("99", "OldName");
        when(artistasDao.findById("99")).thenReturn(Optional.of(existente));
        when(artistasDao.save(entrada)).thenAnswer(inv -> {
            entrada.setId("99");
            return entrada;
        });

        Artista resultado = artistasService.update("99", entrada);

        assertEquals("99", resultado.getId());
        assertEquals("Eve", resultado.getNombre());
        verify(artistasDao).findById("99");
        verify(artistasDao).save(entrada);
    }

    @Test
    void actualizarArtista_deberíaLanzarExcepción_siNoExiste() {
        Artista entrada = new Artista(null, "Foo");
        when(artistasDao.findById("nope")).thenReturn(Optional.empty());

        assertThrows(ArtistaNoEncontradoException.class, () -> artistasService.update("nope", entrada));
    }

    @Test
    void eliminarArtista_deberíaEliminar_siExiste() {
        when(artistasDao.findById("abc")).thenReturn(Optional.of(new Artista("abc", "Zed")));

        artistasService.delete("abc");

        verify(artistasDao).deleteById("abc");
    }

    @Test
    void eliminarArtista_deberíaLanzarExcepción_siNoExiste() {
        when(artistasDao.findById("missing")).thenReturn(Optional.empty());

        assertThrows(ArtistaNoEncontradoException.class, () -> artistasService.delete("missing"));
    }

    @Test
    void obtenerTodasCanciones_deberíaDevolverLista() {
        Cancion c1 = new Cancion("1", Collections.emptyList(), Collections.emptyList(), 0, "rock", "Hit1", "A1");
        Cancion c2 = new Cancion("2", Collections.emptyList(), Collections.emptyList(), 2, "jazz", "Hit2", "A1");
        when(cancionesDao.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Cancion> resultado = cancionesService.getAll();

        assertEquals(2, resultado.size());
    }

    @Test
    void obtenerTodasCanciones_deberíaLanzarExcepción_siVacio() {
        when(cancionesDao.findAll()).thenReturn(Collections.emptyList());

        assertThrows(CancionesNoEncontradasException.class, () -> cancionesService.getAll());
    }

    @Test
    void obtenerCancionPorId_deberíaDevolverCancion_siExiste() {
        Cancion c = new Cancion("9", Collections.emptyList(), Collections.emptyList(), 1, "pop", "Tune", "A2");
        when(cancionesDao.findById("9")).thenReturn(Optional.of(c));

        Cancion resultado = cancionesService.getById("9");

        assertEquals("Tune", resultado.getNombre());
    }

    @Test
    void obtenerCancionPorId_deberíaLanzarExcepción_siNoExiste() {
        when(cancionesDao.findById("none")).thenReturn(Optional.empty());

        assertThrows(CancionNoEncontradaException.class, () -> cancionesService.getById("none"));
    }

    @Test
    void agregarCancion_deberíaGuardarYDevolver() {
        Cancion entrada = new Cancion(null, Collections.emptyList(), Collections.emptyList(), 0, "blues", "BlueSong", "A3");
        Cancion guardada = new Cancion("100", Collections.emptyList(), Collections.emptyList(), 0, "blues", "BlueSong", "A3");
        when(cancionesDao.save(entrada)).thenReturn(guardada);

        Cancion resultado = cancionesService.add(entrada);

        assertEquals("100", resultado.getId());
        verify(cancionesDao).save(entrada);
    }

    @Test
    void eliminarCancion_deberíaEliminar_siExiste() {
        when(cancionesDao.findById("del")).thenReturn(Optional.of(new Cancion("del", null, null, 0, null, null, null)));

        cancionesService.deleteById("del");

        verify(cancionesDao).deleteById("del");
    }

    @Test
    void eliminarCancion_deberíaLanzarExcepción_siNoExiste() {
        when(cancionesDao.findById("gone")).thenReturn(Optional.empty());

        assertThrows(CancionNoEncontradaException.class, () -> cancionesService.deleteById("gone"));
    }

    @Test
    void buscarCancionesPorArtistaId_deberíaDevolverLista_siExisten() {
        ObjectId oid = new ObjectId();
        Cancion c = new Cancion("1", Collections.emptyList(), Collections.emptyList(), 0, "rego", "SongX", oid.toHexString());
        when(cancionesDao.findByArtistaId(oid)).thenReturn(Arrays.asList(c));

        List<Cancion> resultado = cancionesService.findByArtistaId(oid);

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarCancionesPorArtistaId_deberíaLanzarExcepción_siVacio() {
        ObjectId oid = new ObjectId();
        when(cancionesDao.findByArtistaId(oid)).thenReturn(Collections.emptyList());

        assertThrows(CancionesNoEncontradasException.class, () -> cancionesService.findByArtistaId(oid));
    }

    @Test
    void obtenerTodasGrabaciones_deberíaDevolverLista() {
        Grabacion g1 = new Grabacion("1", "U1", "Rec1", "2025-05-01", 3.5, "/path");
        when(grabacionesDao.findAll()).thenReturn(Arrays.asList(g1));

        List<Grabacion> resultado = grabacionesService.getAll();

        assertEquals(1, resultado.size());
    }

    @Test
    void obtenerTodasGrabaciones_deberíaLanzarExcepción_siVacio() {
        when(grabacionesDao.findAll()).thenReturn(Collections.emptyList());

        assertThrows(GrabacionesNoEncontradasException.class, () -> grabacionesService.getAll());
    }

    @Test
    void obtenerGrabacionPorId_deberíaDevolver_siExiste() {
        Grabacion g = new Grabacion("X", "U2", "Rec2", "2025-04-20", 2.0, "/p");
        when(grabacionesDao.findById("X")).thenReturn(Optional.of(g));

        Grabacion resultado = grabacionesService.getById("X");

        assertEquals("Rec2", resultado.getNombre());
    }

    @Test
    void obtenerGrabacionPorId_deberíaLanzarExcepción_siNoExiste() {
        when(grabacionesDao.findById("none")).thenReturn(Optional.empty());

        assertThrows(GrabacionNoEncontradaException.class, () -> grabacionesService.getById("none"));
    }

    @Test
    void agregarGrabacion_deberíaGuardarYDevolver() {
        Grabacion entrada = new Grabacion(null, "U3", "Rec3", "2025-05-15", 4.0, "/pp");
        Grabacion guardada = new Grabacion("77", "U3", "Rec3", "2025-05-15", 4.0, "/pp");
        when(grabacionesDao.save(entrada)).thenReturn(guardada);

        Grabacion resultado = grabacionesService.add(entrada);

        assertEquals("77", resultado.getId());
        verify(grabacionesDao).save(entrada);
    }

    @Test
    void eliminarGrabacion_deberíaEliminar_siExiste() {
        when(grabacionesDao.findById("77")).thenReturn(Optional.of(new Grabacion("77", "", "", "", 0, "")));

        grabacionesService.delete("77");

        verify(grabacionesDao).deleteById("77");
    }

    @Test
    void eliminarGrabacion_deberíaLanzarExcepción_siNoExiste() {
        when(grabacionesDao.findById("no")).thenReturn(Optional.empty());

        assertThrows(GrabacionNoEncontradaException.class, () -> grabacionesService.delete("no"));
    }

    @Test
    void obtenerTodosSonidos_deberíaDevolverLista() {
        Sonido s1 = new Sonido("1", "Snd1", "catA", 1.2, "/s1");
        when(sonidosDao.findAll()).thenReturn(Arrays.asList(s1));

        List<Sonido> resultado = sonidosService.getAll();

        assertFalse(resultado.isEmpty());
    }

    @Test
    void obtenerTodosSonidos_deberíaLanzarExcepción_siVacio() {
        when(sonidosDao.findAll()).thenReturn(Collections.emptyList());

        assertThrows(SonidosNoEncontradosException.class, () -> sonidosService.getAll());
    }

    @Test
    void obtenerSonidoPorId_deberíaDevolver_siExiste() {
        Sonido s = new Sonido("2", "Snd2", "catB", 2.3, "/s2");
        when(sonidosDao.findById("2")).thenReturn(Optional.of(s));

        Sonido resultado = sonidosService.getById("2");

        assertEquals("Snd2", resultado.getNombre());
    }

    @Test
    void obtenerSonidoPorId_deberíaLanzarExcepción_siNoExiste() {
        when(sonidosDao.findById("bad")).thenReturn(Optional.empty());

        assertThrows(SonidoNoEncontradoException.class, () -> sonidosService.getById("bad"));
    }

    @Test
    void buscarSonidosPorCategoria_deberíaDevolverLista_siExisten() {
        Sonido s = new Sonido("3", "Snd3", "catC", 3.4, "/s3");
        when(sonidosDao.findByCategoria("catC")).thenReturn(Arrays.asList(s));

        List<Sonido> resultado = sonidosService.getByCategoria("catC");

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarSonidosPorCategoria_deberíaLanzarExcepción_siVacio() {
        when(sonidosDao.findByCategoria("none")).thenReturn(Collections.emptyList());

        assertThrows(SonidosNoEncontradosException.class, () -> sonidosService.getByCategoria("none"));
    }

    @Test
    void agregarSonido_deberíaGuardarYDevolver() {
        Sonido entrada = new Sonido(null, "New", "catX", 0.5, "/new");
        Sonido guardado = new Sonido("55", "New", "catX", 0.5, "/new");
        when(sonidosDao.save(entrada)).thenReturn(guardado);

        Sonido resultado = sonidosService.add(entrada);

        assertEquals("55", resultado.getId());
    }

    @Test
    void actualizarSonido_deberíaActualizarYDevolver_siExiste() {
        Sonido entrada = new Sonido(null, "Up", "catY", 1.1, "/up");
        when(sonidosDao.findById("99")).thenReturn(Optional.of(new Sonido("99", "Old", "catY", 1.1, "/old")));
        when(sonidosDao.save(entrada)).thenAnswer(inv -> {
            entrada.setId("99");
            return entrada;
        });

        Sonido resultado = sonidosService.update("99", entrada);

        assertEquals("99", resultado.getId());
        assertEquals("Up", resultado.getNombre());
    }

    @Test
    void actualizarSonido_deberíaLanzarExcepción_siNoExiste() {
        Sonido entrada = new Sonido(null, "X", "catZ", 0, "/");
        when(sonidosDao.findById("nope")).thenReturn(Optional.empty());

        assertThrows(SonidoNoEncontradoException.class, () -> sonidosService.update("nope", entrada));
    }

    @Test
    void eliminarSonido_deberíaEliminar_siExiste() {
        when(sonidosDao.findById("del")).thenReturn(Optional.of(new Sonido("del", "", "", 0, "")));

        sonidosService.delete("del");

        verify(sonidosDao).deleteById("del");
    }

    @Test
    void eliminarSonido_deberíaLanzarExcepción_siNoExiste() {
        when(sonidosDao.findById("gone")).thenReturn(Optional.empty());

        assertThrows(SonidoNoEncontradoException.class, () -> sonidosService.delete("gone"));
    }

    @Test
    void agregarUsuario_deberíaGuardarConContraseñaHasheada_siNuevo() {
        Usuario u = new Usuario("usr", "plain");
        when(usuariosDao.findById("usr")).thenReturn(Optional.empty());
        when(usuariosDao.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Usuario resultado = usuariosService.add(u);

        assertEquals("usr", resultado.getUsuario());
        assertNotEquals("plain", resultado.getPassword());
        assertTrue(new BCryptPasswordEncoder().matches("plain", resultado.getPassword()));
    }

    @Test
    void agregarUsuario_deberíaLanzarExcepción_siYaExiste() {
        Usuario existente = new Usuario("usr2", "hash");
        when(usuariosDao.findById("usr2")).thenReturn(Optional.of(existente));

        assertThrows(UsuarioYaExisteException.class, () ->
                usuariosService.add(new Usuario("usr2", "anything"))
        );
    }

    @Test
    void validarUsuario_noDeberíaLanzarExcepción_siCredencialesCorrectas() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode("secret");
        Usuario u = new Usuario("john", pw);
        when(usuariosDao.findById("john")).thenReturn(Optional.of(u));

        assertDoesNotThrow(() -> usuariosService.validate("john", "secret"));
    }

    @Test
    void validarUsuario_deberíaLanzarExcepción_siUsuarioNoExiste() {
        when(usuariosDao.findById("anon")).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () ->
                usuariosService.validate("anon", "any")
        );
    }

    @Test
    void validarUsuario_deberíaLanzarExcepción_siContraseñaIncorrecta() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Usuario u = new Usuario("mary", encoder.encode("right"));
        when(usuariosDao.findById("mary")).thenReturn(Optional.of(u));

        assertThrows(UsuarioNoEncontradoException.class, () ->
                usuariosService.validate("mary", "wrong")
        );
    }
}
