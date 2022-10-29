package com.seminario.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.ResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Formulario")
@RestController
@RequestMapping("/api")
public class FormularioController {

	
    @PostMapping("/formulario/envio")
    @ApiOperation(value = "Carga Formulario")
    public ResponseEntity<ResponseDTO> cargaFormulario(@Valid @RequestBody FormularioRequestDTO formularioDto) {
    	ResponseDTO response = new ResponseDTO();
        return ResponseEntity.ok(response);
    }
    
    
}
