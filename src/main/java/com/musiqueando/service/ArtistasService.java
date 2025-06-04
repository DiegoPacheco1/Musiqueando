package com.musiqueando.service;

import com.musiqueando.dao.ArtistasDao;
import com.musiqueando.domain.Artista;
import com.musiqueando.errors.excepciones.ArtistaNoEncontradoException;
import com.musiqueando.service.common.ConstantesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistasService {

    private final ArtistasDao artistasDao;

    public ArtistasService(ArtistasDao artistasDao) {
        this.artistasDao = artistasDao;
    }

    public List<Artista> getAll() {
        List<Artista> artistas = artistasDao.findAll();
        if (artistas == null || artistas.isEmpty()) {
            throw new ArtistaNoEncontradoException(ConstantesService.ARTISTAS_NO_ENCONTRADOS);
        }
        return artistas;
    }

    public Artista getById(String id) {
        return artistasDao.findById(id)
                .orElseThrow(() ->
                        new ArtistaNoEncontradoException(String.format(ConstantesService.ARTISTA_NO_ENCONTRADO, id))
                );
    }

    public Artista add(Artista artista) {
        return artistasDao.save(artista);
    }

    public Artista update(String id, Artista artista) {
        artistasDao.findById(id)
                .orElseThrow(() ->
                        new ArtistaNoEncontradoException(String.format(ConstantesService.ARTISTA_NO_ENCONTRADO, id))
                );
        artista.setId(id);
        return artistasDao.save(artista);
    }

    public void delete(String id) {
        artistasDao.findById(id)
                .orElseThrow(() ->
                        new ArtistaNoEncontradoException(String.format(ConstantesService.ARTISTA_NO_ENCONTRADO, id))
                );
        artistasDao.deleteById(id);
    }
}
