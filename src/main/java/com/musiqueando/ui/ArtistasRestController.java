package com.musiqueando.ui;

import com.musiqueando.domain.Artista;
import com.musiqueando.service.ArtistasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistasRestController {

    private final ArtistasService artistasService;

    public ArtistasRestController(ArtistasService artistasService) {
        this.artistasService = artistasService;
    }

    @GetMapping
    public ResponseEntity<List<Artista>> getAll() {
        List<Artista> artistas = artistasService.getAll();
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> getById(@PathVariable String id) {
        Artista artista = artistasService.getById(id);
        return ResponseEntity.ok(artista);
    }

    @PostMapping
    public ResponseEntity<Artista> create(@RequestBody Artista artista) {
        Artista creado = artistasService.add(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> update(@PathVariable String id, @RequestBody Artista artista) {
        Artista actualizado = artistasService.update(id, artista);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        artistasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
