package com.springboot.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.models.entitys.Docente;
import com.springboot.models.service.DocenteService;

@Controller
public class DocenteController {
    @Autowired
    private DocenteService service;

    @GetMapping("/listadoDo")
    public String listadoDocente(Model modelo) {
        List<Docente> lista = service.listar();
        modelo.addAttribute("listaDocentes", lista);
        modelo.addAttribute("docente", new Docente());
        return "docente/docente";
    }

    @PostMapping("/guardarDocentes")
    public String guardarDocente(@ModelAttribute Docente doc, RedirectAttributes redirectAttributes) {
        service.guardar(doc);
        redirectAttributes.addFlashAttribute("mensaje", "Docente registrado: " + doc.getUsername());
        return "redirect:/listadoDo";
    }

    @GetMapping("/editarDo/{id}")
    public String editarDocente(@PathVariable("id") int id, Model modelo) {
        Optional<Docente> docenteOptional = service.buscarId(id);
        if (docenteOptional.isPresent()) {
            modelo.addAttribute("docente", docenteOptional.get());
        } else {
            modelo.addAttribute("error", "Docente no encontrado.");
        }
        
        List<Docente> lista = service.listar();
        modelo.addAttribute("listaDocentes", lista);
        return "docente/docente";
    }

    @GetMapping("/borrarDo/{id}")
    public String borrarDocente(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        service.borrar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Docente eliminado con éxito.");
        return "redirect:/listadoDo";
    }
}
