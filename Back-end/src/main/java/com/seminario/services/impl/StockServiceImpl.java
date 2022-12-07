package com.seminario.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.MedicamentoDTO;
import com.seminario.dtos.StockDTO;
import com.seminario.dtos.StockResponseDTO;
import com.seminario.dtos.StockWarningDTO;
import com.seminario.dtos.StockWarningResponseDTO;
import com.seminario.entitys.Stock;
import com.seminario.repositories.StockRepository;
import com.seminario.services.StockService;

@Service
public class StockServiceImpl implements StockService{

	@Autowired
	StockRepository stockRepository;
	
	@Override
	public StockResponseDTO findAllStock(Long idHospital) {
		// TODO Auto-generated method stub
		List<StockDTO> stockListDTO = new ArrayList<StockDTO>();
		List<Stock> stocks = new ArrayList<Stock>();
		if(idHospital != null) {
			stocks = stockRepository.findByIdHospital(idHospital);
		}else {
			stocks = stockRepository.findAll();
		}
		
		for (Stock stock : stocks) {
			StockDTO dto = new StockDTO();
			dto.setHospital(stock.getHospital().getNombre());
			dto.setId(stock.getId());
			dto.setMedicamento(stock.getMedicamento().getNombre());
			dto.setStockReal(stock.getStockReal());
			dto.setStockSeguridad(stock.getStockSeguridad());
			stockListDTO.add(dto);
		}
		return new StockResponseDTO(stockListDTO);
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
	public Stock updateStockPedido (Long idHospital, Long idMedicamento, Long cantMedicamento) {
		Stock stock = stockRepository.findByIdHospitalAndIdMedicamento(idHospital, idMedicamento);
		stock.setStockReal(stock.getStockReal() + cantMedicamento); 
		return stockRepository.save(stock);
	}
	
	@Override
	public Boolean isBelowStockSeguridad(FormularioRequestDTO formularioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockWarningResponseDTO findAllStockWithWarning(Long idHospital) {
		StockWarningResponseDTO response = new StockWarningResponseDTO();
		if (idHospital != null) {
			List<Stock> danger = stockRepository.findStockWithStockUnderStockSeguridadAndByHospital(idHospital);
			List<Stock> warning = stockRepository.findStockWithStockCloseStockSeguridadAndByHosital(idHospital);

			response.setStockWarning(mapStockIntoStockWarningDTO(warning));
			response.setStockDanger(mapStockIntoStockWarningDTO(danger));
		} else {
			List<Stock> danger = stockRepository.findStockWithStockUnderStockSeguridad();
			List<Stock> warning = stockRepository.findStockWithStockCloseStockSeguridad();

			response.setStockWarning(mapStockIntoStockWarningDTO(warning));
			response.setStockDanger(mapStockIntoStockWarningDTO(danger));
		}
		
		return response;
	}

	private List<StockWarningDTO> mapStockIntoStockWarningDTO (List<Stock> stockData) {
		List<StockWarningDTO> list = new ArrayList<StockWarningDTO>();
		for (Stock stock : stockData) {
			StockWarningDTO dto = new StockWarningDTO();
			dto.setHospital(stock.getHospital().getNombre());
			MedicamentoDTO medicamentoDto = new MedicamentoDTO();
			medicamentoDto.setNombre(stock.getMedicamento().getNombre());
			medicamentoDto.setDescripcion(stock.getMedicamento().getDescripcion());
			medicamentoDto.setStock(stock.getStockReal());
			dto.setMedicamento(medicamentoDto);
			list.add(dto);
		}
		return list;
	}
	
}
