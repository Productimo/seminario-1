package com.seminario.dtos;

import java.util.List;

public class StockResponseDTO {

	private List<StockDTO> stock;

	public StockResponseDTO(List<StockDTO> stock) {
		super();
		this.stock = stock;
	}

	public List<StockDTO> getStock() {
		return stock;
	}

	public void setStock(List<StockDTO> stock) {
		this.stock = stock;
	}
}
