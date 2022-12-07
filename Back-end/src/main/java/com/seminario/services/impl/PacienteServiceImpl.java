package com.seminario.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.entitys.Paciente;
import com.seminario.repositories.PacienteRepository;
import com.seminario.services.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Override
	public Paciente generateNewPaciente(FormularioRequestDTO formulario) {
		Paciente paciente = new Paciente();
		paciente.setNombre(formulario.getNombrePaciente());
		paciente.setDni(formulario.getDniPaciente());
		paciente.setGenero(formulario.getGeneroPaciente());
		paciente.setEdad(formulario.getEdadPaciente());
		
		try {
			return pacienteRepository.saveAndFlush(paciente);
		} catch (Exception e) {
			return null;
		}
	}

}
