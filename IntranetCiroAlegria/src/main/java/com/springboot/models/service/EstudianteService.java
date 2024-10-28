package com.springboot.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.models.entitys.Estudiante;

@Service
public interface EstudianteService {
	public List<Estudiante>listar();
	public void guardar(Estudiante est);
	public Optional<Estudiante>buscarId(int id);
	public void borrar(int id);
	public String generarCodigoUsuario(int id);
	public String generarContrasena();
	public Estudiante findByUsernameAndPassword(String username, String password);
	public Estudiante findByNombreCompleto(String nombreCompleto);
	public long totalEstudiantes();
}
