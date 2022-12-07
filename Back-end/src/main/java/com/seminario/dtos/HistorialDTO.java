package com.seminario.dtos;

import java.util.Date;

public class HistorialDTO {
	
	private Long id;
	private String hospital;
	private Long idPedido;
	private Long idFormulario;
	private Long idMedicamento;
	private String nombreMedicamento;
	private Long StockReponer;
	private Long StockUtilizado;
	private Long dniPaciente;
	private String nombrePaciente;
	private Date fechaCarga;
	private Long stockSnapshot;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public Long getIdFormulario() {
		return idFormulario;
	}
	public void setIdFormulario(Long idFormulario) {
		this.idFormulario = idFormulario;
	}
	public Long getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public String getNombreMedicamento() {
		return nombreMedicamento;
	}
	public void setNombreMedicamento(String nombreMedicamento) {
		this.nombreMedicamento = nombreMedicamento;
	}
	public Long getStockReponer() {
		return StockReponer;
	}
	public void setStockReponer(Long stockReponer) {
		StockReponer = stockReponer;
	}
	public Long getDniPaciente() {
		return dniPaciente;
	}
	public void setDniPaciente(Long dniPaciente) {
		this.dniPaciente = dniPaciente;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public Date getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public Long getStockSnapshot() {
		return stockSnapshot;
	}
	public void setStockSnapshot(Long stockSnapshot) {
		this.stockSnapshot = stockSnapshot;
	}
	public Long getStockUtilizado() {
		return StockUtilizado;
	}
	public void setStockUtilizado(Long stockUtilizado) {
		StockUtilizado = stockUtilizado;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
}
