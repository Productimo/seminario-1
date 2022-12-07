package com.seminario.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminario.dtos.HospitalDTO;
import com.seminario.dtos.HospitalResponseDTO;
import com.seminario.dtos.PacienteHospitalDTO;
import com.seminario.dtos.PacienteHospitalResponseDTO;
import com.seminario.entitys.Hospital;
import com.seminario.entitys.MedicamentoUsoBean;
import com.seminario.repositories.FormularioRepository;
import com.seminario.repositories.HospitalRepository;
import com.seminario.services.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService{

	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private FormularioRepository formularioRepository;
	
	@Override
	public HospitalResponseDTO getHospitales() {
		List<Hospital> entitys = hospitalRepository.findAll();
		List<HospitalDTO> hospitalesDto = new ArrayList<HospitalDTO>();
		for (Hospital hospital : entitys) {
			HospitalDTO dto = new HospitalDTO();
			dto.setDireccion(hospital.getDireccion());
			dto.setId(hospital.getId());
			dto.setNombre(hospital.getNombre());
			hospitalesDto.add(dto);
		}
		return new HospitalResponseDTO(hospitalesDto);
	}

	@Override
	public PacienteHospitalResponseDTO pacientesPorHospital() {
		List<MedicamentoUsoBean> list = formularioRepository.pacienteGroupByHospital();
		List<PacienteHospitalDTO> listDto = new ArrayList<PacienteHospitalDTO>();
		for (MedicamentoUsoBean bean : list) {
			Hospital hospital = hospitalRepository.getById(bean.getIdMedicamento());
			PacienteHospitalDTO dto = new PacienteHospitalDTO(hospital != null ? hospital.getNombre() : "", bean.getTotal());
			listDto.add(dto);
		}
		
		return new PacienteHospitalResponseDTO(listDto);
	}

}
