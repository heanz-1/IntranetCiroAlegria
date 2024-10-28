package com.springboot.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.models.entitys.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    Docente findByUsername(String username);
}
