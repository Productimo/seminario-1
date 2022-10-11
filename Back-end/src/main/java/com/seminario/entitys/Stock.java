package com.seminario.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="id_hospital", nullable=false)
    private Hospital hospital;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_medicamento", referencedColumnName = "id")
    private Medicamento medicamento;
	
	@Column(name = "stock_real")
	private Long stockReal;
	
	@Column(name = "stock_seguridad")
	private Long stockSeguridad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Long getStockReal() {
		return stockReal;
	}

	public void setStockReal(Long stockReal) {
		this.stockReal = stockReal;
	}

	public Long getStockSeguridad() {
		return stockSeguridad;
	}

	public void setStockSeguridad(Long stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}
}
