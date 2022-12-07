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
@Table(name = "historial")
public class Historial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "id_stock", insertable = false,  updatable = false)
	private Long idStock;
	
	@Column(name = "id_pedido", insertable = false,  updatable = false)
	private Long idPedido;
	
	@ManyToOne
	@JoinColumn(name = "id_stock", nullable = false)
	private Stock stock;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", nullable = true)
	private Pedido pedido;
	
	@OneToOne(mappedBy="historial")
	private Formulario formulario;
	
	@Column(name = "fecha_carga")
	private Date fechaCarga;
	
	@Column(name = "stock_snapshot")
	private Long stockSnapshot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public Long getStockSnapshot() {
		return stockSnapshot;
	}

	public void setStockSnapshot(Long stockSnapshot) {
		this.stockSnapshot = stockSnapshot;
	}
}
