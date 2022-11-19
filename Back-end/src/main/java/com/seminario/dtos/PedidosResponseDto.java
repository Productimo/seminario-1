package com.seminario.dtos;

import java.util.List;

public class PedidosResponseDto {
	
	private List<PedidoDTO> pedidos;

	public List<PedidoDTO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}
}
