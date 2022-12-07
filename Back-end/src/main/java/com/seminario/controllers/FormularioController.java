package com.seminario.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.FormularioResponseDTO;
import com.seminario.dtos.ResponseDTO;
import com.seminario.services.FormularioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Formulario")
@RestController
@RequestMapping("/api")
public class FormularioController {

	@Autowired
	private FormularioService formularioService;
	
    @PostMapping("/formulario/envio")
    @ApiOperation(value = "Carga Formulario")
    public ResponseEntity<ResponseDTO> cargaFormulario(@Valid @RequestBody FormularioRequestDTO formularioDto) {
    	ResponseDTO response = formularioService.cargaFormulario(formularioDto);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/formulario")
    @ApiOperation(value = "Buscar Formularios")
    public ResponseEntity<FormularioResponseDTO> getFormularios(@RequestParam(required = false) Long idHospital) {
        return ResponseEntity.ok(formularioService.getFormularios(idHospital));
    }
}
