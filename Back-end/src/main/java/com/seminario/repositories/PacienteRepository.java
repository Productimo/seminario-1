package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
