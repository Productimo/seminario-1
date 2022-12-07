package com.seminario.dtos;

public class PedidoPorAnoDTO {
	
	private String mes;
	private Long total1;
	private Long total2;
	
	public PedidoPorAnoDTO(String mes, Long total1, Long total2) {
		super();
		this.mes = mes;
		this.total1 = total1;
		this.total2 = total2;
	}
	public PedidoPorAnoDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Long getTotal1() {
		return total1;
	}
	public void setTotal1(Long total1) {
		this.total1 = total1;
	}
	public Long getTotal2() {
		return total2;
	}
	public void setTotal2(Long total2) {
		this.total2 = total2;
	}
	
	
}
