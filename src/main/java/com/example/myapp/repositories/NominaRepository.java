package com.example.myapp.repositories;

import java.util.List;
import com.example.myapp.domain.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myapp.domain.Nomina;

public interface NominaRepository extends JpaRepository<Nomina, Long>{
    List<Nomina> findByEmpleado(Empleado empleado);
}