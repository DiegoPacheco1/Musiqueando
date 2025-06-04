package com.musiqueando.ui;

import com.musiqueando.domain.Grabacion;
import com.musiqueando.service.GrabacionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grabaciones")
public class GrabacionesRestController {

    private final GrabacionesService grabacionesService;

    public GrabacionesRestController(GrabacionesService grabacionesService) {
        this.grabacionesService = grabacionesService;
    }

    @GetMapping
    public ResponseEntity<List<Grabacion>> getAll() {
        List<Grabacion> grabaciones = grabacionesService.getAll();
        return ResponseEntity.ok(grabaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grabacion> getById(@PathVariable String id) {
        Grabacion grabacion = grabacionesService.getById(id);
        return ResponseEntity.ok(grabacion);
    }

    @PostMapping
    public ResponseEntity<Grabacion> create(@RequestBody Grabacion grabacion) {
        Grabacion creada = grabacionesService.add(grabacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        grabacionesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
