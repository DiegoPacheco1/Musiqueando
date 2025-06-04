package com.musiqueando.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sonidos")
public class Sonido {
    @Id
    private String id;
    private String nombre;
    private String categoria;
    private double duracion;
    private String path;
}
