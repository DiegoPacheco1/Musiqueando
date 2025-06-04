package com.musiqueando.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "canciones")
public class Cancion {
    @Id
    private String id;
    private List<String> acordes;
    private List<String> letra;
    private Integer cejilla;
    private String genero;
    private String nombre;
    private String artistaId;
}

