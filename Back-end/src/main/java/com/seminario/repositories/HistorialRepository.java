package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Historial;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
	
}
