package com.seminario.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.dtos.HospitalResponseDTO;
import com.seminario.dtos.PacienteHospitalResponseDTO;
import com.seminario.services.HospitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Hospitales")
@RestController
@RequestMapping("/api")
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;

	@GetMapping("/hospital")
    @ApiOperation(value = "get all hospitales")
    public ResponseEntity<HospitalResponseDTO> findAllStock() throws ParseException {
        return ResponseEntity.ok(hospitalService.getHospitales());
    }
	
	@GetMapping("/hospital/pacientes")
    @ApiOperation(value = "get total de pacientes por hospital")
    public ResponseEntity<PacienteHospitalResponseDTO> pacientesPorHospital() throws ParseException {
        return ResponseEntity.ok(hospitalService.pacientesPorHospital());
    }
	
}
