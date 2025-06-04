package com.musiqueando.ui;

import com.musiqueando.domain.Cancion;
import com.musiqueando.service.CancionesService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionesRestController {

    private final CancionesService cancionesService;

    public CancionesRestController(CancionesService cancionesService) {
        this.cancionesService = cancionesService;
    }

    /** Lista todas las canciones */
    @GetMapping
    public ResponseEntity<List<Cancion>> getAll() {
        List<Cancion> canciones = cancionesService.getAll();
        return ResponseEntity.ok(canciones);
    }

    /** Obtiene una canción por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Cancion> getById(@PathVariable String id) {
        Cancion cancion = cancionesService.getById(id);
        return ResponseEntity.ok(cancion);
    }

    /** Obtiene canciones por artista (usando artistaId) */
    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<List<Cancion>> getByArtista(@PathVariable("artistaId") ObjectId artistaId) {
        List<Cancion> canciones = cancionesService.findByArtistaId(artistaId);
        return ResponseEntity.ok(canciones);
    }

    /** Crea una nueva canción */
    @PostMapping
    public ResponseEntity<Cancion> create(@RequestBody Cancion cancion) {
        Cancion creada = cancionesService.add(cancion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    /** Elimina una canción */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cancionesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
