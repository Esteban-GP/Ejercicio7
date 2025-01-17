package com.example.myapp.services;

import java.util.List;
import com.example.myapp.domain.Departamento;

public interface DepartamentoService {
    public List<Departamento> obtenerDepartamentos();
    public void anadir(Departamento departamento);
    public Departamento obtenerPorNombre(String nombre);
}
