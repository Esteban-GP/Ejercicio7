package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myapp.domain.Nomina;

public interface NominaRepository extends JpaRepository<Nomina, Long>{
}