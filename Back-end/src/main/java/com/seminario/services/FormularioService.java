package com.seminario.services;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.ResponseDTO;

public interface FormularioService {

	ResponseDTO cargaFormulario (FormularioRequestDTO formulario);
}
