package com.seminario.dtos;

import java.util.List;

public class PedidoRequestDTO {
	
	private List<MedicamentoRequestDTO> pedidos;
	private String tipoEnvio;
	private Long idHospital;
	
	public List<MedicamentoRequestDTO> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<MedicamentoRequestDTO> pedidos) {
		this.pedidos = pedidos;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public Long getIdHospital() {
		return idHospital;
	}
	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}	
}
