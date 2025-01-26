package com.example.myapp.services;

import java.util.List;

import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**Creamos una interfaz EmpleadoService con los métodos que necesitamos para gestionar los empleados, para descoplar el código
En este caso, la implementaremos en una clase EmpleadoServiceImplExcep.java que contendrá la lógica de negocio y la inyectamos en el controlador. 
No inyectamos la implementación de esta interfaz (EmpleadoServiceImpleExcep), para así independizar el controlador de la implementación.
Si desarrollamos otra interfaz, podemos utilzar la anotación @Qualifier ("nombre") para indicar cuál de las dos queremos inyectar en el controlador,
además de poder indicar la anotación @Primary en la implementación que queramos que se inyecte por defecto. 
Cada implementación debe tener un nombre @Qualifier ("nombre2")**/

public interface EmpleadoService {
    Empleado anadir(Empleado empleado);

    List<Empleado> obtenerTodos();

    Empleado obtenerPorId(long id) throws RuntimeException;

    Empleado editar(Empleado empleado) throws RuntimeException;

    void borrar(Long id) throws RuntimeException;

    List<Empleado> buscarPorNombre(String nombre);

    List<Empleado> buscarPorGenero(Genero genero);

    List<Empleado> buscarPorDepartamento(String nombreDepartamento);

    Page<Empleado> obtenerTodos(Pageable pageable);
}
