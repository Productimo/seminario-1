package com.seminario.services;


import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.StockResponseDTO;
import com.seminario.dtos.StockWarningResponseDTO;
import com.seminario.entitys.Stock;

public interface StockService {

	Stock findByHospitalAndMedicamento(Long idHospital, Long idMedicamento);
	Stock updateStock(Long idHospital, Long idMedicamento, Integer cantMedicamento);
	Boolean isBelowStockSeguridad (FormularioRequestDTO formularioDTO);
	StockWarningResponseDTO findAllStockWithWarning(Long idHospital);
	StockResponseDTO findAllStock(Long idHospital);
	Stock updateStockPedido(Long idHospital, Long idMedicamento, Long stockReposicion);
}
