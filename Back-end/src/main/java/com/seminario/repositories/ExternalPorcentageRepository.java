package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.ExternalPorcentage;

public interface ExternalPorcentageRepository extends JpaRepository<ExternalPorcentage, Long>{

	ExternalPorcentage findTopByOrderByIdDesc();
}
