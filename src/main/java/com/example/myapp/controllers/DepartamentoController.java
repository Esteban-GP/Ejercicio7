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
import com.example.myapp.domain.Genero;
import com.example.myapp.domain.Departamento;
import com.example.myapp.services.DepartamentoService;
import com.example.myapp.services.EmpleadoService;

/*
    /list   (o simplemente / ) 	: listar los datos de todos los empleados
    /{id}					    : obtener los datos solo del empleado ‘id’
    /nuevo  				    : formulario para añadir un nuevo empleado
    /editar/{id} 			    : formulario de edición para modificar empleado ‘id’
    /borrar/{id}				: eliminar el empleado ‘id’
 */

@Controller
public class DepartamentoController {

    @Autowired
    public EmpleadoService empleadoService;

    @Autowired
    public DepartamentoService departamentoService;

    private String txtMsg;

    @GetMapping("/departamentos/")
    public String getMethodName(Model model) {
        model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
        return "listDepartamentos";
    }

    @GetMapping("/findByDepartamento/{nombreDep}")
    public String showFindByGenero(@PathVariable String nombreDep, Model model) {
        model.addAttribute("listaEmpleados", empleadoService.buscarPorDepartamento(nombreDep));
        model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
        model.addAttribute("findForm", new Empleado());
        model.addAttribute("departamentoSelccionado", nombreDep);
        return "listView";
    }

    // http://localhost:9000/editar/1
    @GetMapping("/editarDEP/{id}")
    public String showEditDEP(@PathVariable long id, Model model) {
        try {
            Departamento departamento = departamentoService.obtenerPorId(id);
            model.addAttribute("departamentoForm", departamento);
            model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
            return "editDepartamentoView";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
    }

    // http://localhost:9000/editar/1/submit
    @PostMapping("/editarDEP/{id}/submit")
    // Usamos @PathVariable para obtener el id del empleado a editar, el cual forma
    // parte de la URL (no es un parámtero del formulario)
    public String showEditSubmitDEP(@PathVariable Long id, @Valid Departamento departamentoForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            txtMsg = "Error en formulario";
            return "redirect:/";
        }
        try {
            departamentoService.editar(departamentoForm);
            txtMsg = "Operación realizada con éxito";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
        }
        return "redirect:/";
    }

    // http://localhost:9000/borrar/1
    @GetMapping("/borrarDEP/{id}")
    public String showDeleteDEP(@PathVariable long id) {
        departamentoService.borrarPorId(id);
        txtMsg = "Operación realizada con éxito";
        return "redirect:/";
    }
}
