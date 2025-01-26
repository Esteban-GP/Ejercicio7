package com.example.myapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Nomina {
        @Id
        @GeneratedValue
        private Long id;
    
        @ManyToOne
        @OnDelete (action = OnDeleteAction.CASCADE)
        @ToString.Exclude
        private Empleado empleado;

        private String fecha;

        private Double salario;
}
