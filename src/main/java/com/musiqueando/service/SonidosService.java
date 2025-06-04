package com.musiqueando.service;

import com.musiqueando.dao.SonidosDao;
import com.musiqueando.domain.Sonido;
import com.musiqueando.errors.excepciones.SonidoNoEncontradoException;
import com.musiqueando.errors.excepciones.SonidosNoEncontradosException;
import com.musiqueando.service.common.ConstantesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SonidosService {

    private final SonidosDao sonidosDao;

    public SonidosService(SonidosDao sonidosDao) {
        this.sonidosDao = sonidosDao;
    }

    public List<Sonido> getAll() {
        List<Sonido> sonidos = sonidosDao.findAll();
        if (sonidos == null || sonidos.isEmpty()) {
            throw new SonidosNoEncontradosException(ConstantesService.SONIDOS_NO_ENCONTRADOS);
        }
        return sonidos;
    }

    public Sonido getById(String id) {
        return sonidosDao.findById(id)
                .orElseThrow(() ->
                        new SonidoNoEncontradoException(String.format(ConstantesService.SONIDO_NO_ENCONTRADO, id))
                );
    }

    public List<Sonido> getByCategoria(String categoria) {
        List<Sonido> sonidos = sonidosDao.findByCategoria(categoria);
        if (sonidos == null || sonidos.isEmpty()) {
            throw new SonidosNoEncontradosException(ConstantesService.SONIDOS_NO_ENCONTRADOS_POR_CATEGORIA + categoria);
        }
        return sonidos;
    }

    public Sonido add(Sonido sonido) {
        return sonidosDao.save(sonido);
    }

    public Sonido update(String id, Sonido sonido) {
        sonidosDao.findById(id)
                .orElseThrow(() ->
                        new SonidoNoEncontradoException(String.format(ConstantesService.SONIDO_NO_ENCONTRADO, id))
                );
        sonido.setId(id);
        return sonidosDao.save(sonido);
    }

    public void delete(String id) {
        sonidosDao.findById(id)
                .orElseThrow(() ->
                        new SonidoNoEncontradoException(String.format(ConstantesService.SONIDO_NO_ENCONTRADO, id))
                );
        sonidosDao.deleteById(id);
    }
}
