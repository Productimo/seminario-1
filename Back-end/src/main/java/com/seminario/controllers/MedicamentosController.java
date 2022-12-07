package com.seminario.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.dtos.MedicamentoResposeDTO;
import com.seminario.dtos.MedicamentoUsoResponseDTO;
import com.seminario.services.FormularioService;
import com.seminario.services.MedicamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Medicamentos")
@RestController
@RequestMapping("/api")
public class MedicamentosController {
	
	@Autowired
	private FormularioService formularioService;
	
	@Autowired
	private MedicamentoService medicamentoService;
	
	@GetMapping("/medicamento/uso")
    @ApiOperation(value = "uso de medicamentos filtrado por fecha y hospital")
    public ResponseEntity<MedicamentoUsoResponseDTO> findAllStock(
    		@RequestParam(required = false) Long idHospital, @RequestParam String mes1, @RequestParam String mes2) throws ParseException {
        return ResponseEntity.ok(formularioService.fetchMedicamentoUso(mes1, mes2, idHospital));
    }
	
	@GetMapping("/medicamento")
    @ApiOperation(value = "get all medicamentos")
    public ResponseEntity<MedicamentoResposeDTO> findAllStock() throws ParseException {
        return ResponseEntity.ok(medicamentoService.getMedicamentos());
    }
}
