package com.example.myapp.domain;

import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmpleadoDTO {

    private Long id;

    private String nombre;

    @Email(message = "Debe tener formato email valido")
    private String email;

    private String departamento;

    private Double salario;

    private boolean enActivo;

    private Genero genero;
}
