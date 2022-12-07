package com.seminario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.dtos.HistorialResponseDTO;
import com.seminario.services.HistorialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Historial")
@RestController
@RequestMapping("/api")
public class HistorialController {

	@Autowired
	private HistorialService historialService;
	
    @GetMapping("/historial")
    @ApiOperation(value = "trae el historial")
    public ResponseEntity<HistorialResponseDTO> getHistorial(@RequestParam(required = false) Long idHospital) {
        return ResponseEntity.ok(historialService.getHistorial(idHospital));
    }
}
