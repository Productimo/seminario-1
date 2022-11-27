package com.seminario.dtos;

import java.util.List;

public class StockWarningResponseDTO {

	private List<StockWarningDTO> stockWarning;
	private List<StockWarningDTO> stockDanger;
	public List<StockWarningDTO> getStockWarning() {
		return stockWarning;
	}
	public void setStockWarning(List<StockWarningDTO> stockWarning) {
		this.stockWarning = stockWarning;
	}
	public List<StockWarningDTO> getStockDanger() {
		return stockDanger;
	}
	public void setStockDanger(List<StockWarningDTO> stockDanger) {
		this.stockDanger = stockDanger;
	}
	
	
}
