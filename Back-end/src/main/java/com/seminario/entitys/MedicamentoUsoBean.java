package com.seminario.entitys;

public class MedicamentoUsoBean {

	private Long total;
	private Integer mes;
	private Long idHospital;
	private Long idMedicamento;
	
	public MedicamentoUsoBean(Long total, Long idHospital, Long idMedicamento) {
		super();
		this.total = total;
		this.idHospital = idHospital;
		this.idMedicamento = idMedicamento;
	}

	public MedicamentoUsoBean(Long total, Long idHospital, Long idMedicamento, Integer mes) {
		super();
		this.total = total;
		this.idHospital = idHospital;
		this.idMedicamento = idMedicamento;
		this.mes = mes;
	}
	
	public MedicamentoUsoBean(Long total, Long idMedicamento) {
		super();
		this.total = total;
		this.idMedicamento = idMedicamento;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getIdHospital() {
		return idHospital;
	}
	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}
	public Long getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
}
