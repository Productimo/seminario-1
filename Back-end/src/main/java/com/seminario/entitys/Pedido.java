package com.seminario.entitys;

import java.util.Date;

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
@Table(name = "pedidos")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "id_hospital")
	private Long idHospital;

	@Column(name = "id_estado")
	private Long idEstado;
	
	@Column(name = "id_medicamento")
	private Long idMedicamento;
	
	@ManyToOne
    @JoinColumn(name="id_hospital", nullable=false)
    private Hospital hospital;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
	private EstadoPedido estado;
	
	@ManyToOne
    @JoinColumn(name="id_medicamento", nullable=false)
    private Medicamento medicamento;
	
	@Column(name = "fecha_pedido")
	private Date fechaPedido;
	
	@Column(name = "stock_a_reponer")
	private Long stockReposicion;
	
	@Column(name = "comentario")
	private String comentario;

	@Column(name = "urgencia")
	private Boolean urgencia;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Long getStockReposicion() {
		return stockReposicion;
	}

	public void setStockReposicion(Long stockReposicion) {
		this.stockReposicion = stockReposicion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(Boolean urgencia) {
		this.urgencia = urgencia;
	}
}
