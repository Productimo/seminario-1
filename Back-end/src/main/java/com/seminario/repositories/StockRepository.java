package com.seminario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	List<Stock> findByIdHospital(Long idHospital);
}