package com.seminario.services;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.entitys.Paciente;

public interface PacienteService {

	Paciente generateNewPaciente(FormularioRequestDTO formulario);
}
