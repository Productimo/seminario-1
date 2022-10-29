package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
