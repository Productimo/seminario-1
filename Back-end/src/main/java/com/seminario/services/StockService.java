package com.seminario.services;

import java.util.List;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.entitys.Stock;

public interface StockService {

	List<Stock> findAllStock();
	List<Stock> findAllStockByHospital(Long idHospital);
	Stock findByHospitalAndMedicamento(Long idHospital, Long idMedicamento);
	Stock updateStock(Long idHospital, Long idMedicamento, Integer cantMedicamento);
	Boolean isBelowStockSeguridad (FormularioRequestDTO formularioDTO);
}
