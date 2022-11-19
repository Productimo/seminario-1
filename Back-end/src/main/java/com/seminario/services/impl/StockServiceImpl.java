package com.seminario.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.entitys.Stock;
import com.seminario.repositories.StockRepository;
import com.seminario.services.StockService;

@Service
public class StockServiceImpl implements StockService{

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

	@Override
	public Stock findByHospitalAndMedicamento(Long idHospital, Long idMedicamento) {
		return stockRepository.findByIdHospitalAndIdMedicamento(idHospital, idMedicamento);
	}
	
	@Override
	public Stock updateStock (Long idHospital, Long idMedicamento, Integer cantMedicamento) {
		Stock stock = stockRepository.findByIdHospitalAndIdMedicamento(idHospital, idMedicamento);
		stock.setStockReal(stock.getStockReal() - cantMedicamento); 
		return stockRepository.save(stock);
	}

	@Override
	public Boolean isBelowStockSeguridad(FormularioRequestDTO formularioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
