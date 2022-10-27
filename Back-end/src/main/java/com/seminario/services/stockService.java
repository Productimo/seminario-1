package com.seminario.services;

import java.util.List;

import com.seminario.entitys.Stock;

public interface stockService {

	List<Stock> findAllStock();
	List<Stock> findAllStockByHospital(Long idHospital);
}
