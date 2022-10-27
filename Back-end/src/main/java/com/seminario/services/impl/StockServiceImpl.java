package com.seminario.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminario.entitys.Stock;
import com.seminario.repositories.StockRepository;
import com.seminario.services.stockService;

@Service
public class StockServiceImpl implements stockService{

	@Autowired
	StockRepository stockRepository;
	
	@Override
	public List<Stock> findAllStock() {
		// TODO Auto-generated method stub
		return stockRepository.findAll();
	}

	@Override
	public List<Stock> findAllStockByHospital(Long idHospital) {
		// TODO Auto-generated method stub
		return stockRepository.findByIdHospital(idHospital);
	}

}
