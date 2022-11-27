package com.seminario.services;

import com.seminario.dtos.HospitalResponseDTO;
import com.seminario.dtos.PacienteHospitalResponseDTO;

public interface HospitalService {
	
	HospitalResponseDTO getHospitales();

	PacienteHospitalResponseDTO pacientesPorHospital();

}
