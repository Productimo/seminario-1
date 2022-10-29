package com.seminario.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.entitys.Stock;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/stock")
@Api(tags = "Stock")
public class StockController {

	@Autowired
	private com.seminario.services.stockService stockService;
	
	@GetMapping("/all")
    @ApiOperation(value = "User Logout")
    public ResponseEntity<List<Stock>> findAllStock(
            @RequestHeader(required = true, value = "Authorization") String authHeader) {
        return ResponseEntity.ok(stockService.findAllStock());
    }
	
	@GetMapping("/byHospital")
    @ApiOperation(value = "User Logout")
    public ResponseEntity<List<Stock>> findAllStockByHospital(
            @RequestParam Long idHospital) {
        return ResponseEntity.ok(stockService.findAllStockByHospital(idHospital));
    }
	//localhost:8080/api/stock/byHospital?idHospital=1234
}
