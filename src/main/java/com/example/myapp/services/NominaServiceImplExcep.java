package com.example.myapp.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Nomina;
import com.example.myapp.repositories.NominaRepository;


@Service
public class NominaServiceImplExcep implements NominaService {

    @Autowired
    private NominaRepository nominaRepository;

    public List<Nomina> obtenerNominas() {
        List<Nomina> nominas = nominaRepository.findAll();
        return nominas;
    }

    public void anadir(Nomina nomina) {
        nominaRepository.save(nomina);
    }

    public Nomina obtenerPorId(Long id) {
        Nomina nomina = nominaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nomina no encontrado"));
        return nomina;
    }

    public void borrar(Long id) {
        nominaRepository.deleteById(id);
    }

    public void editar(Nomina Nomina) {
        nominaRepository.save(Nomina);
    }
}
