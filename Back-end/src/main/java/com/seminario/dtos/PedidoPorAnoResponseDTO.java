package com.seminario.dtos;

import java.util.List;

public class PedidoPorAnoResponseDTO {
	
	private List<PedidoPorAnoDTO> pedidos;

	public PedidoPorAnoResponseDTO(List<PedidoPorAnoDTO> pedidos) {
		super();
		this.pedidos = pedidos;
	}

	public PedidoPorAnoResponseDTO() {
	}

	public List<PedidoPorAnoDTO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoPorAnoDTO> pedidos) {
		this.pedidos = pedidos;
	}

}
