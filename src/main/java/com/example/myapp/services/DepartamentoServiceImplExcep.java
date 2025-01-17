package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Departamento;
import com.example.myapp.repositories.DepartamentoRepository;

@Service
public class DepartamentoServiceImplExcep implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> obtenerDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return departamentos;
    }

    public void anadir(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    public Departamento obtenerPorNombre(String nombre) {
        Departamento departamento = departamentoRepository.findByNombre(nombre);
        return departamento;
    }       
}
