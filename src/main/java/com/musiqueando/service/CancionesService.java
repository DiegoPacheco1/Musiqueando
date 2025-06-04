package com.musiqueando.service;

import com.musiqueando.dao.CancionesDao;
import com.musiqueando.domain.Cancion;
import com.musiqueando.errors.excepciones.CancionNoEncontradaException;
import com.musiqueando.errors.excepciones.CancionesNoEncontradasException;
import com.musiqueando.service.common.ConstantesService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancionesService {

    private final CancionesDao cancionesDao;


    public CancionesService(CancionesDao cancionesDao) {
        this.cancionesDao = cancionesDao;
    }

    public List<Cancion> getAll() {
        List<Cancion> canciones = cancionesDao.findAll();
        if (canciones == null || canciones.isEmpty()) {
            throw new CancionesNoEncontradasException(ConstantesService.CANCIONES_NO_ENCONTRADAS);
        }
        return canciones;
    }

    public Cancion getById(String id) {
        return cancionesDao.findById(id)
                .orElseThrow(() ->
                        new CancionNoEncontradaException(ConstantesService.CANCION_NO_ENCONTRADA + id)
                );
    }

    public Cancion add(Cancion cancion) {
        return cancionesDao.save(cancion);
    }

    public void deleteById(String id) {
        cancionesDao.findById(id)
                .orElseThrow(() ->
                        new CancionNoEncontradaException(ConstantesService.CANCION_NO_ELIMINADA + id + ", no existe.")
                );
        cancionesDao.deleteById(id);
    }

    public List<Cancion> findByArtistaId(ObjectId artistaId) {
        List<Cancion> canciones = cancionesDao.findByArtistaId(artistaId);
        if (canciones == null || canciones.isEmpty()) {
            throw new CancionesNoEncontradasException(
                    ConstantesService.CANCIONES_NO_ENCONTRADAS_POR_ARTISTA + artistaId
            );
        }
        return canciones;
    }
}
