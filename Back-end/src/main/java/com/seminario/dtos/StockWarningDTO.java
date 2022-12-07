package com.seminario.dtos;

public class StockWarningDTO {

	private String hospital;
	private MedicamentoDTO medicamento;
	
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public MedicamentoDTO getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(MedicamentoDTO medicamento) {
		this.medicamento = medicamento;
	}
}
