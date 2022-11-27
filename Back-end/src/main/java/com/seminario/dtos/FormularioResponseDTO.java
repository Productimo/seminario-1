package com.seminario.dtos;

import java.util.List;

public class FormularioResponseDTO {

	List<FormularioDTO> formularios;
	
	public FormularioResponseDTO(List<FormularioDTO> formularios) {
		super();
		this.formularios = formularios;
	}

	public List<FormularioDTO> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<FormularioDTO> formularios) {
		this.formularios = formularios;
	}
}
