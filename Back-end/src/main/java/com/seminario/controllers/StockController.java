package com.seminario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.dtos.StockResponseDTO;
import com.seminario.dtos.StockWarningResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/stock")
@Api(tags = "Stock")
public class StockController {

	@Autowired
	private com.seminario.services.StockService stockService;
	
	@GetMapping("/all")
    @ApiOperation(value = "busca stock en la base y tiene filtro de hospital")
    public ResponseEntity<StockResponseDTO> findAllStock(
    		@RequestParam(required = false) Long idHospital) {
        return ResponseEntity.ok(stockService.findAllStock(idHospital));
    }
	
	@GetMapping("/all/warning")
    @ApiOperation(value = "User Logout")
    public ResponseEntity<StockWarningResponseDTO> findAllStockWithWarning(
            @RequestParam(required = false) Long idHospital) {
        return ResponseEntity.ok(stockService.findAllStockWithWarning(idHospital));
    }
}
