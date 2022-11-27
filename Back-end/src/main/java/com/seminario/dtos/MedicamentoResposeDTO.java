package com.seminario.dtos;

import java.util.List;

public class MedicamentoResposeDTO {

	List<MedicamentoDTO> medicamentos;
	
	public MedicamentoResposeDTO(List<MedicamentoDTO> medicamentos) {
		super();
		this.medicamentos = medicamentos;
	}

	public List<MedicamentoDTO> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}
}
