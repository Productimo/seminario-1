package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

}
