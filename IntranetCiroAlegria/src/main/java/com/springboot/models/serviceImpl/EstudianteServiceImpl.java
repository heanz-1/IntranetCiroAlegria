package com.springboot.models.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.models.entitys.Estudiante;
import com.springboot.models.repository.EstudianteRepository;
import com.springboot.models.service.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	@Autowired
	private EstudianteRepository repository;

	@Override
	public List<Estudiante> listar() {
		
		return repository.findAll();
	}

	@Override
	public void guardar(Estudiante est) {
		repository.save(est);
		String codigoUsuario = generarCodigoUsuario(est.getIdestudiante());
        String contrasena = generarContrasena();

        // En este caso, no encriptamos la contraseña
        est.setUsername(codigoUsuario);
        est.setPassword(contrasena);
        
        repository.save(est);

	}

	@Override
	public Optional<Estudiante> buscarId(int id) {
		
		return repository.findById(id);
	}

	@Override
	public void borrar(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public Estudiante findByUsernameAndPassword(String username, String password) {
		Estudiante estudiante = repository.findByUsername(username);
        if (estudiante != null && password.equals(estudiante.getPassword())) {
            return estudiante;
        }
        return null;
	}

	@Override
    public String generarCodigoUsuario(int id) {
        return "d" + 2023 + id;
    }

	@Override
    public String generarContrasena() {
        int longitud = 8;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder contrasena = new StringBuilder(longitud);
        Random random = new Random();

        for (int i = 0; i < longitud; i++) {
            contrasena.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        return contrasena.toString();
    }

	@Override
	public long totalEstudiantes() {
		return repository.count();
	}

	@Override
	public Estudiante findByNombreCompleto(String nombreCompleto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
}
