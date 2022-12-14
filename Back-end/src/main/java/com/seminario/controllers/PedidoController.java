package com.seminario.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.dtos.PedidoCargaResponseDTO;
import com.seminario.dtos.PedidoPorAnoResponseDTO;
import com.seminario.dtos.PedidoRequestDTO;
import com.seminario.dtos.PedidosResponseDto;
import com.seminario.dtos.ResponseDTO;
import com.seminario.services.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pedido")
@RestController
@RequestMapping("/api")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
    @PostMapping("/pedido/envio")
    @ApiOperation(value = "Carga Pedido")
    public ResponseEntity<PedidoCargaResponseDTO> envioPedido(@Valid @RequestBody PedidoRequestDTO pedidoDto) {
    	PedidoCargaResponseDTO response = pedidoService.cargaPedido(pedidoDto);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/pedido/estado")
    @ApiOperation(value = "Update Estado Pedido")
    public ResponseEntity<ResponseDTO> updatePedidoEstado(@RequestParam Long idPedido) {
    	ResponseDTO response = pedidoService.updateEstadoPedido(idPedido);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/pedido")
    @ApiOperation(value = "get Pedidos")
    public ResponseEntity<PedidosResponseDto> getPedidos(@RequestParam(required = false) Long idHospital) {
    	PedidosResponseDto response = pedidoService.getPedidos(idHospital);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/pedido/year")
    @ApiOperation(value = "get Pedidos por ano")
    public ResponseEntity<PedidoPorAnoResponseDTO> getPedidosPorAno(@RequestParam(required = false) Long idHospital, Long year1, Long year2) throws ParseException {
    	PedidoPorAnoResponseDTO response = pedidoService.getPedidosPorAno(idHospital, year1, year2);
        return ResponseEntity.ok(response);
    }
    
    
}
