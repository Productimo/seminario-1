package com.seminario.services;

import java.text.ParseException;

import javax.validation.Valid;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.PedidoCargaResponseDTO;
import com.seminario.dtos.PedidoPorAnoResponseDTO;
import com.seminario.dtos.PedidoRequestDTO;
import com.seminario.dtos.PedidosResponseDto;
import com.seminario.dtos.ResponseDTO;
import com.seminario.entitys.Hospital;
import com.seminario.entitys.Medicamento;
import com.seminario.entitys.Pedido;
import com.seminario.entitys.Stock;

public interface PedidoService {

	Pedido generateNewPedido(FormularioRequestDTO formularioDTO, Hospital hospital, Medicamento medicamento, Stock stock);

	PedidoCargaResponseDTO cargaPedido(@Valid PedidoRequestDTO pedidoDto);
	
	ResponseDTO updateEstadoPedido(Long idPedido);

	PedidosResponseDto getPedidos(Long idHospital);

	PedidoPorAnoResponseDTO getPedidosPorAno(Long idHospital, Long year1, Long year2) throws ParseException;

}
