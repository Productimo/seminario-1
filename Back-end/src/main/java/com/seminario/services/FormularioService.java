package com.seminario.services;

import java.text.ParseException;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.FormularioResponseDTO;
import com.seminario.dtos.MedicamentoUsoResponseDTO;
import com.seminario.dtos.ResponseDTO;

public interface FormularioService {

	ResponseDTO cargaFormulario (FormularioRequestDTO formulario);
	
	MedicamentoUsoResponseDTO fetchMedicamentoUso(String mes1, String mes2, Long idHospital) throws ParseException;
	
	FormularioResponseDTO getFormularios(Long idHospital);
	
}
