package com.springboot.models.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.models.entitys.Docente;
import com.springboot.models.repository.DocenteRepository;
import com.springboot.models.service.DocenteService;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository repository;

    @Override
    public List<Docente> listar() {
        return repository.findAll();
    }

    @Override
    public void guardar(Docente doc) {
    	String codigoUsuario = generarCodigoUsuario(doc.getIddocente());
        String contrasena = generarContrasena();
        
        doc.setUsername(codigoUsuario);
        doc.setPassword(contrasena);
        
        repository.save(doc); // Solo una llamada a save
    }

    @Override
    public Docente findByUsernameAndPassword(String username, String password) {
        Docente docente = repository.findByUsername(username);
        if (docente != null && password.equals(docente.getPassword())) {
            return docente;
        }
        return null;
    }

    @Override
    public Optional<Docente> buscarId(int id) {
        return repository.findById(id);
    }

    @Override
    public void borrar(int id) {
        repository.deleteById(id);
    }

    @Override
    public String generarCodigoUsuario(int id) {
        return "d" + System.currentTimeMillis();
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
    public long totalDocentes() {
        return repository.count();
    }

    @Override
    public Docente findByNombreCompleto(String nombreCompleto) {
        return null;
    }

}
