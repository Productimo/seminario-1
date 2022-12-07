package com.seminario.entitys;

public class PedidosPorAnoBean {

	private Integer mes;
	private Long total;
	
	public PedidosPorAnoBean(Integer mes, Long total) {
		super();
		this.mes = mes;
		this.total = total;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
