package com.springboot.models.service;

import java.util.List;
import java.util.Optional;
import com.springboot.models.entitys.Docente;


public interface DocenteService {
	public List<Docente>listar();
	public void guardar(Docente doc);
	public Optional<Docente>buscarId(int id);
	public void borrar(int id);
	public String generarCodigoUsuario(int id);
	public String generarContrasena();
	public Docente findByUsernameAndPassword(String username, String password);
	public Docente findByNombreCompleto(String nombreCompleto);
	long totalDocentes();
}
