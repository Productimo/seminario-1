package com.seminario.dtos;


public class FormularioRequestDTO {
	
	private Long dniPaciente;
	private String nombrePaciente;
	private Integer matricula;
	private Integer idMedicamento;
	private Long idUsuario;
	private Integer cantAmpollas;
	private String comentarios;
	
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
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Integer getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public Integer getCantAmpollas() {
		return cantAmpollas;
	}
	public void setCantAmpollas(Integer cantAmpollas) {
		this.cantAmpollas = cantAmpollas;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
}
