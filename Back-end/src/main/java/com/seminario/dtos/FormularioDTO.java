package com.seminario.dtos;

public class FormularioDTO {

	private Long id;
	private String hospital;
	private String medico;
	private String medicoMatricula;
	private String medicamento;
	private String fechaAtencion;
	private String descripcion;
	private Integer cantMedicamentoUsado;
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
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getMedicoMatricula() {
		return medicoMatricula;
	}
	public void setMedicoMatricula(String medicoMatricula) {
		this.medicoMatricula = medicoMatricula;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantMedicamentoUsado() {
		return cantMedicamentoUsado;
	}
	public void setCantMedicamentoUsado(Integer cantMedicamentoUsado) {
		this.cantMedicamentoUsado = cantMedicamentoUsado;
	}
}
