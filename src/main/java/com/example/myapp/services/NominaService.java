package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myapp.domain.Departamento;
import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import com.example.myapp.domain.Nomina;
import com.example.myapp.repositories.DepartamentoRepository;

/**Creamos una interfaz EmpleadoService con los métodos que necesitamos para gestionar los empleados, para descoplar el código
En este caso, la implementaremos en una clase EmpleadoServiceImplExcep.java que contendrá la lógica de negocio y la inyectamos en el controlador. 
No inyectamos la implementación de esta interfaz (EmpleadoServiceImpleExcep), para así independizar el controlador de la implementación.
Si desarrollamos otra interfaz, podemos utilzar la anotación @Qualifier ("nombre") para indicar cuál de las dos queremos inyectar en el controlador,
además de poder indicar la anotación @Primary en la implementación que queramos que se inyecte por defecto. 
Cada implementación debe tener un nombre @Qualifier ("nombre2")**/

public interface NominaService {
    List<Nomina> obtenerNominas();
    void anadir(Nomina nomina);
    Nomina obtenerPorId(Long id) throws RuntimeException;
    void editar(Nomina nomina) throws RuntimeException;
    void borrar(Long id) throws RuntimeException;
}
