package com.musiqueando.service;

import com.musiqueando.dao.GrabacionesDao;
import com.musiqueando.domain.Grabacion;
import com.musiqueando.errors.excepciones.GrabacionNoEncontradaException;
import com.musiqueando.errors.excepciones.GrabacionesNoEncontradasException;
import com.musiqueando.service.common.ConstantesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrabacionesService {

    private final GrabacionesDao grabacionesDao;

    public GrabacionesService(GrabacionesDao grabacionesDao) {
        this.grabacionesDao = grabacionesDao;
    }

    public List<Grabacion> getAll() {
        List<Grabacion> grabaciones = grabacionesDao.findAll();
        if (grabaciones == null || grabaciones.isEmpty()) {
            throw new GrabacionesNoEncontradasException(ConstantesService.GRABACIONES_NO_ENCONTRADAS);
        }
        return grabaciones;
    }

    public Grabacion getById(String id) {
        return grabacionesDao.findById(id)
                .orElseThrow(() ->
                        new GrabacionNoEncontradaException(String.format(ConstantesService.GRABACION_NO_ENCONTRADA, id))
                );
    }

    public Grabacion add(Grabacion grabacion) {
        return grabacionesDao.save(grabacion);
    }


    public void delete(String id) {
        grabacionesDao.findById(id)
                .orElseThrow(() ->
                        new GrabacionNoEncontradaException(String.format(ConstantesService.GRABACION_NO_ENCONTRADA, id))
                );
        grabacionesDao.deleteById(id);
    }
}
