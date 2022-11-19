package com.seminario.dtos;

public class PedidoRequestDTO {
	
	private Long idMedicamento;
	private Long idHospital;
	private String tipoEnvio;
	private Integer cantMedicamento;
	private String comentarios;
	
	public Long getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public Long getIdHospital() {
		return idHospital;
	}
	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public Integer getCantMedicamento() {
		return cantMedicamento;
	}
	public void setCantMedicamento(Integer cantMedicamento) {
		this.cantMedicamento = cantMedicamento;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
