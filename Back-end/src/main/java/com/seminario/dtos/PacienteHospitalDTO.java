package com.seminario.dtos;

public class PacienteHospitalDTO {

	private String hospital;
	private Long total;
	
	public PacienteHospitalDTO(String hospital, Long total) {
		super();
		this.hospital = hospital;
		this.total = total;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
