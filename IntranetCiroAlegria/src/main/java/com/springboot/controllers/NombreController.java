package com.springboot.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springboot.models.entitys.Docente;
import com.springboot.models.entitys.Estudiante;

@ControllerAdvice
public class NombreController {

    @ModelAttribute
    public void addUserInfo(Model model) {
        if (model.containsAttribute("docente")) {
            Docente docente = (Docente) model.getAttribute("docente");
            model.addAttribute("nombreCompleto", docente.getNombre() + " " + docente.getApellido());
        } 
        else if (model.containsAttribute("estudiante")) {
            Estudiante estudiante = (Estudiante) model.getAttribute("estudiante");
            model.addAttribute("nombreCompleto", estudiante.getNombre() + " " + estudiante.getApellido());
        }
    }
}
