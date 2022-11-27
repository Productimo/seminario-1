package com.seminario.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seminario.dtos.MedicamentoDTO;
import com.seminario.dtos.MedicamentoResposeDTO;
import com.seminario.entitys.Medicamento;
import com.seminario.repositories.MedicamentoRepository;
import com.seminario.services.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{

	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	@Override
	public MedicamentoResposeDTO getMedicamentos() {
		List<Medicamento> entitys = medicamentoRepository.findAll();
		List<MedicamentoDTO> medicamentos = new ArrayList<MedicamentoDTO>();
		for (Medicamento medicamento : entitys) {
			MedicamentoDTO dto = new MedicamentoDTO();
			dto.setDescripcion(medicamento.getDescripcion());
			dto.setId(medicamento.getId());
			dto.setNombre(medicamento.getNombre());
			medicamentos.add(dto);
		}
		return new MedicamentoResposeDTO(medicamentos);
	}

}
