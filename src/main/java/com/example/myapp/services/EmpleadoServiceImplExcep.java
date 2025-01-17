package com.example.myapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import com.example.myapp.repositories.EmpleadoRepository;

@Service
public class EmpleadoServiceImplExcep implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private List<Empleado> repositorio = new ArrayList<>();
    private final Double MIN_SALAR = 18000D;

    public List<Empleado> obtenerTodos() {
        repositorio = empleadoRepository.findAll();
        return repositorio;
    }

    public Empleado obtenerPorId(long id) throws RuntimeException {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isEmpty())
            throw new RuntimeException("Empleado no encontrado");
        else {
            return empleado.get();
        }

    }

    public Empleado anadir(Empleado empleado) throws RuntimeException {
        empleadoRepository.save(empleado);
        return empleado;
    }

    public Empleado editar(Empleado empleado) throws RuntimeException {
        // indexOf devuelve la posición del objeto empleado en la lista, si no lo
        // encuentra devuelve -1
        // En el controlador se captura la excepción y se muestra un mensaje de error
        if (!empleadoRepository.existsById(empleado.getId()))
            throw new RuntimeException("Empleado no encontrado");
        if (empleado.getSalario() < MIN_SALAR)
            throw new RuntimeException("Salario muy bajo");
        empleadoRepository.save(empleado);
        return empleado;
    }

    public void borrar(Long id) throws RuntimeException {
        Empleado empleado = this.obtenerPorId(id);
        empleadoRepository.delete(empleado);
    }

    public List<Empleado> buscarPorNombre(String textoNombre) {
        textoNombre = textoNombre.toLowerCase();
        List<Empleado> encontrados = new ArrayList<>();
        for (Empleado empleado : repositorio)
            if (empleado.getNombre().toLowerCase().contains(textoNombre))
                encontrados.add(empleado);
        return encontrados;
    }

    public List<Empleado> buscarPorGenero(Genero genero) {
        List<Empleado> encontrados = new ArrayList<>();
        for (Empleado empleado : repositorio)
            if (empleado.getGenero() == genero)
                encontrados.add(empleado);
        return encontrados;
    }

    public List<Empleado> buscarPorDepartamento(String nombreDepartamento) {
        List<Empleado> encontrados = new ArrayList<>();
        for (Empleado empleado : repositorio)
            if (empleado.getDepartamento().getNombre().equals(nombreDepartamento))
                encontrados.add(empleado);
        return encontrados;
    }
}
