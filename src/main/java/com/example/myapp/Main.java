package com.example.myapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myapp.domain.Departamento;
import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import com.example.myapp.services.EmpleadoService;
import com.example.myapp.services.DepartamentoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(EmpleadoService empleadoService, DepartamentoService departamentoService) {
		return args -> {
			Departamento marketing = new Departamento(1L, "Marketing", "Departamento encargado de promover las ventas");
			departamentoService.anadir(marketing);
			Departamento rrhh = new Departamento(2L, "Recursos Humanos", "Departamento encargado de la gestión de personal");
			departamentoService.anadir(rrhh);
			empleadoService.anadir(
					new Empleado(1L, "López Pérez, José", "jlp@gmail.com", marketing , 25000d, true, Genero.MASCULINO));
			empleadoService.anadir(new Empleado(2L, "García Martínez, Ana", "ana_garcia@gmail.com", marketing, 20000d, false,
					Genero.FEMENINO));
			empleadoService.anadir(new Empleado(3L, "Martínez Ruiz, Juan", "juanmr@gmail.com", marketing, 25000d, true,
					Genero.MASCULINO));
			empleadoService.anadir(
					new Empleado(4L, "Pérez Gómez, María", "mariapg@gmail.com", marketing, 18000d, true, Genero.FEMENINO));
			empleadoService.anadir(new Empleado(5L, "Gómez Sánchez, Pedro", "pedro_sanchez@gmail.com", marketing, 22000d, false,
					Genero.MASCULINO));
			empleadoService.anadir(new Empleado(6L, "Sánchez López, Laura", "laurasl@gmail.com", rrhh, 27000d, true,
					Genero.FEMENINO));
			empleadoService.anadir(
					new Empleado(7L, "Ruiz García, David", "davidruiz@gmail.com", rrhh, 19000d, false, Genero.OTROS));
			empleadoService.anadir(new Empleado(8L, "Pérez Martínez, Carmen", "carmenpm@gmail.com", rrhh, 29000d, true,
					Genero.OTROS));
			empleadoService.anadir(new Empleado(9L, "Martínez Gómez, Sergio", "sergiomg@gmail.com", rrhh, 27000d, false,
					Genero.MASCULINO));
			empleadoService.anadir(new Empleado(10L, "López Sánchez, Isabel", "isalopez@gmail.com", rrhh, 24000d, true,
					Genero.FEMENINO));

		};
	}

}
