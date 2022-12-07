package com.seminario.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminario.dtos.HistorialDTO;
import com.seminario.dtos.HistorialResponseDTO;
import com.seminario.entitys.Historial;
import com.seminario.repositories.HistorialRepository;
import com.seminario.services.HistorialService;

@Service
public class HistorialServiceImpl implements HistorialService{

	@Autowired
	private HistorialRepository historialRepository;
	
	@Override
	public HistorialResponseDTO getHistorial(Long idHospital) {
		List<Historial> entitys = historialRepository.findAll();
		List<HistorialDTO> historialDto = new ArrayList<HistorialDTO>();
		List<Long> pacientes = new ArrayList<Long>();
		if (idHospital != null) {
			for (Historial entity : entitys) {
				if(entity.getFormulario().getIdHospital() == idHospital) {
					HistorialDTO dto = mapDto(entity);
					historialDto.add(dto);
					if(!pacientes.contains(dto.getDniPaciente())) {
						pacientes.add(dto.getDniPaciente());
					}
				}
			}
			return new HistorialResponseDTO(historialDto, pacientes.size());
		}
		
		for (Historial entity : entitys) {
			HistorialDTO dto = mapDto(entity);
			historialDto.add(dto);
			if(!pacientes.contains(dto.getDniPaciente())) {
				pacientes.add(dto.getDniPaciente());
			}
		}
		
		return new HistorialResponseDTO(historialDto, pacientes.size());
	}

	private HistorialDTO mapDto(Historial entity) {
		HistorialDTO dto = new HistorialDTO();
		dto.setDniPaciente(entity.getFormulario().getIdPaciente());
		dto.setHospital(entity.getFormulario().getHospital().getNombre());
		dto.setFechaCarga(entity.getFechaCarga());
		dto.setId(entity.getId());
		dto.setIdFormulario(entity.getFormulario().getId());
		dto.setIdMedicamento(entity.getFormulario().getIdMedicamento());
		dto.setIdPedido(entity.getPedido().getId());
		dto.setNombreMedicamento(entity.getFormulario().getMedicamento().getNombre());
		dto.setNombrePaciente(entity.getFormulario().getPaciente().getNombre());
		dto.setStockReponer(entity.getPedido().getStockReposicion());
		dto.setStockSnapshot(entity.getStockSnapshot());
		dto.setStockUtilizado(entity.getFormulario().getCantMedicamentoUsado().longValue());
		return dto;
	}

	
	
}
