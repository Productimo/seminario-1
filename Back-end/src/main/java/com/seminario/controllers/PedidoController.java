package com.seminario.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}
	
    @PostMapping("/pedido/envio")
    @ApiOperation(value = "Carga Pedido")
    public ResponseEntity<PedidosResponseDto> envioPedido(@Valid @RequestBody PedidoRequestDTO pedidoDto) {
    	PedidosResponseDto response = pedidoService.cargaPedido(pedidoDto);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/pedido/estado")
    @ApiOperation(value = "Update Estado Pedido")
    public ResponseEntity<ResponseDTO> updatePedidoEstado(@Valid @RequestParam Long idPedido) {
    	ResponseDTO response = pedidoService.updateEstadoPedido(idPedido);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/pedido")
    @ApiOperation(value = "get Pedidos")
    public ResponseEntity<PedidosResponseDto> getPedidos() {
    	PedidosResponseDto response = pedidoService.getPedidos();
        return ResponseEntity.ok(response);
    }
}
