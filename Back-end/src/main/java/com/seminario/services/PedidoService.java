package com.seminario.services;

import javax.validation.Valid;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.PedidoRequestDTO;
import com.seminario.dtos.ResponseDTO;
import com.seminario.entitys.Hospital;
import com.seminario.entitys.Medicamento;
import com.seminario.entitys.Pedido;
import com.seminario.entitys.Stock;

public interface PedidoService {

	Pedido generateNewPedido(FormularioRequestDTO formularioDTO, Hospital hospital, Medicamento medicamento, Stock stock);

	ResponseDTO cargaFormulario(@Valid PedidoRequestDTO pedidoDto);
	
	ResponseDTO updateEstadoPedido(Long idPedido);

}
