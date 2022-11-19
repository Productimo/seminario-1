package com.seminario.dtos;

import java.util.Date;

public class PedidoDTO {

	private String hospital;
	private Long idPedido;
	private String medicamento;
	private Date fechaPedido;
	private Integer cantMedicamento;
	private String tipoPedido;
	private String estado;
	
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public Integer getCantMedicamento() {
		return cantMedicamento;
	}
	public void setCantMedicamento(Integer cantMedicamento) {
		this.cantMedicamento = cantMedicamento;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
