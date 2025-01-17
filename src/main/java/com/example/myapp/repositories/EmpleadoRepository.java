package com.example.myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    List<Empleado> findByNombre(String nombre);
    List<Empleado> findByGenero(Genero genero);
}