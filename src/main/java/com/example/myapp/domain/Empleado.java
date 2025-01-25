package com.example.myapp.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Empleado {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    @Email(message = "Debe tener formato email valido")
    private String email;

    @ManyToOne
    private Departamento departamento;

    private Double salario;

    private boolean enActivo;

    private Genero genero;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
    mappedBy="empleado")
    private List<Nomina> nominas = new ArrayList<>();
}
