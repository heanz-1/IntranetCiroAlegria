package com.springboot.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.models.entitys.Estudiante;
import com.springboot.models.service.EstudianteService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class EstudianteController {
@Autowired
private EstudianteService service;
	@GetMapping("/listado")
	public String listado(Model modelo) {
		List<Estudiante>lista = service.listar();
		modelo.addAttribute("listaEstudiantes",lista);
		modelo.addAttribute("estudiantes", new Estudiante());
		return "estudiante/estudiante";
	}
	
	@PostMapping("/guardarEstudiante")
	public String guardar(@ModelAttribute Estudiante est) {
		service.guardar(est);
		return "redirect:/listado";
	}
	
	@GetMapping("/editar/{id}")
	public String getMethodName(@PathVariable("id") int id, Model modelo){
		Optional<Estudiante>listar = service.buscarId(id);
		modelo.addAttribute("estudiantes",listar);
		List<Estudiante>lista = service.listar();
		modelo.addAttribute("listaEstudiantes",lista);
		return "estudiante/estudiante";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") int id) {
		service.borrar(id);
		return "redirect:/listado";
	}
	
	@GetMapping("/total")
	public String dashboard(Model model) {
	    long totalEstudiantes = service.totalEstudiantes();
	    model.addAttribute("totalEstudiantes", totalEstudiantes);
	    return "index";
	}
	
}
