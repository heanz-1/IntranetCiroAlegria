package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.models.entitys.Docente;
import com.springboot.models.entitys.Estudiante;
import com.springboot.models.service.DocenteService;
import com.springboot.models.service.EstudianteService;

@Controller
@SessionAttributes({"docente", "estudiante", "nombreCompleto"})
public class LoginController {

    @Autowired
    private DocenteService docenteService;
    
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", "");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                        @RequestParam String password, 
                        @RequestParam String role, 
                        RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", null); 

        if ("docente".equals(role)) {
            Docente docente = docenteService.findByUsernameAndPassword(username, password);
            if (docente != null) {
                redirectAttributes.addFlashAttribute("nombreCompleto", docente.getNombre() + " " + docente.getApellido());
                return "redirect:/index"; 
            } else {
                redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
            }
        } 
        // Autenticación de estudiante
        else if ("estudiante".equals(role)) {
            Estudiante estudiante = estudianteService.findByUsernameAndPassword(username, password);
            if (estudiante != null) {
                redirectAttributes.addFlashAttribute("nombreCompleto", estudiante.getNombre() + " " + estudiante.getApellido()); 
                return "redirect:/index";
            } else {
                redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
            }
        }

        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String home(Model model) {
        long totalEstudiantes = estudianteService.totalEstudiantes();
        long totalDocentes = docenteService.totalDocentes();
        model.addAttribute("totalEstudiantes", totalEstudiantes);
        model.addAttribute("totalDocentes", totalDocentes);
        return "index";
    }
}
