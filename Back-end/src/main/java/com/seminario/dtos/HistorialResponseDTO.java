package com.seminario.dtos;

import java.util.List;

public class HistorialResponseDTO {

	private Integer pacientes;
	private List<HistorialDTO> historial;
	
	public HistorialResponseDTO(List<HistorialDTO> historial, int pacientes) {
		super();
		this.historial = historial;
		this.pacientes = pacientes;
	}

	public List<HistorialDTO> getHistorial() {
		return historial;
	}

	public void setHistorial(List<HistorialDTO> historial) {
		this.historial = historial;
	}

	public Integer getPacientes() {
		return pacientes;
	}

	public void setPacientes(Integer pacientes) {
		this.pacientes = pacientes;
	}
	
}
