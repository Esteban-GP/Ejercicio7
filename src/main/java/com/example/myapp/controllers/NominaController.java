package com.example.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Nomina;
import com.example.myapp.domain.Departamento;
import com.example.myapp.services.DepartamentoService;
import com.example.myapp.services.EmpleadoService;
import com.example.myapp.services.NominaService;
import org.springframework.web.bind.annotation.RequestParam;


/*
    /list   (o simplemente / ) 	: listar los datos de todos los empleados
    /{id}					    : obtener los datos solo del empleado ‘id’
    /nuevo  				    : formulario para añadir un nuevo empleado
    /editar/{id} 			    : formulario de edición para modificar empleado ‘id’
    /borrar/{id}				: eliminar el empleado ‘id’
 */

@Controller
public class NominaController {

    @Autowired
    public EmpleadoService empleadoService;

    @Autowired
    public DepartamentoService departamentoService;

    @Autowired
    public NominaService nominaService;

    @GetMapping("/addNomina/{id}")
    public String addNomina(@PathVariable long id, Model model) {
        try {
            Empleado empleado = empleadoService.obtenerPorId(id);
            model.addAttribute("empleado", empleado);
            model.addAttribute("nominaForm", new Nomina());
            return "addNominaView";
        } catch (RuntimeException e) {
            return "redirect:/";
        }
    }

    @PostMapping("/addNomina/{id}/submit")
    public String addNominaSubmit(@PathVariable long id, @Valid Nomina nominaForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        try {
            Empleado empleado = empleadoService.obtenerPorId(id);
            nominaForm.setEmpleado(empleado);
            nominaService.anadir(nominaForm);
        } catch (RuntimeException e) {
            return "redirect:/";
        }
        return "redirect:/";
    }


    // http://localhost:9000/editar/1
    @GetMapping("/editarNOM/{id}")
    public String showEditNOM(@PathVariable long id, Model model) {
        try {
            Nomina nomina = nominaService.obtenerPorId(id);
            model.addAttribute("nominaForm", nomina);
            return "editNominaView";
        } catch (RuntimeException e) {
            return "redirect:/";
        }
    }

    // http://localhost:9000/editar/1/submit
    @PostMapping("/editarNOM/{id}/submit")
    // Usamos @PathVariable para obtener el id del empleado a editar, el cual forma
    // parte de la URL (no es un parámtero del formulario)
    public String showEditSubmitNOM(@PathVariable Long id, @Valid Nomina nominaForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        try {
            nominaService.editar(nominaForm);
        } catch (RuntimeException e) {
            return "redirect:/";
        }
        return "redirect:/";
    }

    // http://localhost:9000/borrar/1
    @GetMapping("/borrarNOM/{id}")
    public String showDeleteNOM(@PathVariable long id) {
        nominaService.borrar(id);
        return "redirect:/";
    }
}
