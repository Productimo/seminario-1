package com.seminario.dtos;

import java.util.List;

public class HospitalResponseDTO {

	List<HospitalDTO> hospitales;
	
	public HospitalResponseDTO(List<HospitalDTO> hospitales) {
		super();
		this.hospitales = hospitales;
	}

	public List<HospitalDTO> getHospitales() {
		return hospitales;
	}

	public void setHospitales(List<HospitalDTO> hospitales) {
		this.hospitales = hospitales;
	}
}
