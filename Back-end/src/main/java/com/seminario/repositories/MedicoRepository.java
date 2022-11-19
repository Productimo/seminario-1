package com.seminario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	List<Medico> findByMatricula(String matricula);
}
