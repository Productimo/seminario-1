package com.seminario.dtos;

public class StockDTO {

	private Long id;
	private String hospital;
	private String medicamento;
	private Long stockReal;
	private Long stockSeguridad;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public Long getStockReal() {
		return stockReal;
	}
	public void setStockReal(Long stockReal) {
		this.stockReal = stockReal;
	}
	public Long getStockSeguridad() {
		return stockSeguridad;
	}
	public void setStockSeguridad(Long stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}
	
	
}
