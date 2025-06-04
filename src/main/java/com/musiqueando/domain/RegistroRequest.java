package com.musiqueando.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {
    private String usuario;
    private String password;
    private String mail;
}
