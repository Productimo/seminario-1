package com.seminario.dtos;

import java.util.List;

public class PacienteHospitalResponseDTO {

	private List<PacienteHospitalDTO> pacientes;

	public PacienteHospitalResponseDTO(List<PacienteHospitalDTO> pacientes) {
		super();
		this.pacientes = pacientes;
	}

	public List<PacienteHospitalDTO> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<PacienteHospitalDTO> pacientes) {
		this.pacientes = pacientes;
	}
	
	
}
