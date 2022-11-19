package com.seminario.dtos;

import java.util.List;

public class PedidosResponseDto {
	
	private List<ResponseDTO> pedidos;

	public List<ResponseDTO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<ResponseDTO> pedidos) {
		this.pedidos = pedidos;
	}
}
