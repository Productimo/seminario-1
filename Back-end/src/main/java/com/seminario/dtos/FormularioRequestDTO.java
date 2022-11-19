package com.seminario.dtos;

import java.util.Date;

public class FormularioRequestDTO {
	
	private Long dniPaciente;
	private String nombrePaciente;
	private String matricula;
	private Long idMedicamento;
	private Long idHospital;
	private Integer cantAmpollas;
	private String comentarios;
	private String generoPaciente;
	private Integer edadPaciente;
	private Date fechaAtencion;
	
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
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Long getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public Integer getCantAmpollas() {
		return cantAmpollas;
	}
	public void setCantAmpollas(Integer cantAmpollas) {
		this.cantAmpollas = cantAmpollas;
	}
	public Long getIdHospital() {
		return idHospital;
	}
	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getGeneroPaciente() {
		return generoPaciente;
	}
	public void setGeneroPaciente(String generoPaciente) {
		this.generoPaciente = generoPaciente;
	}
	public Integer getEdadPaciente() {
		return edadPaciente;
	}
	public void setEdadPaciente(Integer edadPaciente) {
		this.edadPaciente = edadPaciente;
	}
	public Date getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
}
