package com.example.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;

import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import com.example.myapp.services.DepartamentoService;
import com.example.myapp.services.EmpleadoService;
import com.example.myapp.services.NominaService;



/*
    /list   (o simplemente / ) 	: listar los datos de todos los empleados
    /{id}					    : obtener los datos solo del empleado ‘id’
    /nuevo  				    : formulario para añadir un nuevo empleado
    /editar/{id} 			    : formulario de edición para modificar empleado ‘id’
    /borrar/{id}				: eliminar el empleado ‘id’
 */

@Controller
public class EmpleadoController {

    @Autowired
    public EmpleadoService empleadoService;

    @Autowired
    public DepartamentoService departamentoService;

    @Autowired
    public NominaService nominasService;

    private String txtMsg;
//http://localhost:9000
    @GetMapping({ "/", "/list" })
    public String showList(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Empleado> empleadoPage = empleadoService.obtenerTodos(pageable);
        model.addAttribute("listaEmpleados", empleadoPage.getContent());
        model.addAttribute("totalPages", empleadoPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
        model.addAttribute("findForm", new Empleado());
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "listView";
    }

//http://localhost:9000/1
    @GetMapping("/{id}")
    public String showElement(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("empleado", empleadoService.obtenerPorId(id));
            return "listOneView";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
    }

//http://localhost:9000/nuevo
    @GetMapping("/nuevo")
    public String showNew(Model model) {
        model.addAttribute("empleadoForm", new Empleado());
        model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
        return "newFormView";
    }

//http://localhost:9000/nuevo/submit
    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid Empleado empleadoForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            txtMsg = "Error en formulario";
            return "redirect:/";
        }
        try {
            empleadoService.anadir(empleadoForm);
            txtMsg = "Operación realizada con éxito";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
        }
        return "redirect:/";
    }

//http://localhost:9000/editar/1
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            Empleado empleado = empleadoService.obtenerPorId(id);
            model.addAttribute("empleadoForm", empleado);
            model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
            model.addAttribute("listaNominas", nominasService.obtenerPorEmpleado(empleado));
            return "editFormView";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
    }

//http://localhost:9000/editar/1/submit
    @PostMapping("/editar/{id}/submit")
    //Usamos @PathVariable para obtener el id del empleado a editar, el cual forma parte de la URL (no es un parámtero del formulario)
    public String showEditSubmit(@PathVariable Long id, @Valid Empleado empleadoForm
            , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            txtMsg = "Error en formulario";
            return "redirect:/";
        }
        try {
            empleadoService.editar(empleadoForm);
            txtMsg = "Operación realizada con éxito";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
        }
        return "redirect:/";
    }

//http://localhost:9000/borrar/1
    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        empleadoService.borrar(id);
        txtMsg = "Operación realizada con éxito";
        return "redirect:/";
    }

//http://localhost:9000/findByName
    @PostMapping("/findByName")
    public String showFindByNameSubmit(Empleado empleadoForm, Model model) {
        model.addAttribute("listaEmpleados", empleadoService.buscarPorNombre(empleadoForm.getNombre()));
        model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
        model.addAttribute("findForm", empleadoForm);
        return "listView";
    }
    
//http://localhost:9000/findByGenero/MASCULINO
    @GetMapping("/findByGenero/{genero}")
    public String showFindByGenero(@PathVariable Genero genero, Model model) {
        model.addAttribute("listaEmpleados", empleadoService.buscarPorGenero(genero));
        model.addAttribute("listaDepartamentos", departamentoService.obtenerDepartamentos());
        model.addAttribute("findForm",new Empleado());
        model.addAttribute("generoSeleccionado",genero);
        return "listView";
    }

}
