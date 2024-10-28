package com.springboot.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.models.entitys.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer>{
	Estudiante findByUsername(String username);
}
