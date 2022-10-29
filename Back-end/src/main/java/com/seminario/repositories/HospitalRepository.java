package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	
	Hospital findByNombre (String nombre);

}
