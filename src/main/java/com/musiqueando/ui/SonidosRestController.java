package com.musiqueando.ui;

import com.musiqueando.domain.Sonido;
import com.musiqueando.service.SonidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sonidos")
public class SonidosRestController {

    private final SonidosService sonidosService;

    public SonidosRestController(SonidosService sonidosService) {
        this.sonidosService = sonidosService;
    }

    @GetMapping
    public ResponseEntity<List<Sonido>> getAll() {
        List<Sonido> sonidos = sonidosService.getAll();
        return ResponseEntity.ok(sonidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sonido> getById(@PathVariable String id) {
        Sonido sonido = sonidosService.getById(id);
        return ResponseEntity.ok(sonido);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Sonido>> getByCategoria(@PathVariable String categoria) {
        List<Sonido> sonidos = sonidosService.getByCategoria(categoria);
        return ResponseEntity.ok(sonidos);
    }

    @PostMapping
    public ResponseEntity<Sonido> create(@RequestBody Sonido sonido) {
        Sonido creado = sonidosService.add(sonido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sonido> update(@PathVariable String id, @RequestBody Sonido sonido) {
        Sonido actualizado = sonidosService.update(id, sonido);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        sonidosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
