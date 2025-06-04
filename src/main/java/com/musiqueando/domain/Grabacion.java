package com.musiqueando.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "grabaciones")
public class Grabacion {
    @Id
    private String id;
    private String usuarioId;
    private String nombre;
    private String fecha;
    private double duracion;
    private String path;
}
