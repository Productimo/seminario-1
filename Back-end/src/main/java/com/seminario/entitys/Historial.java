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
}
