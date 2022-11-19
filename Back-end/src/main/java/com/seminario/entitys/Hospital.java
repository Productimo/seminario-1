package com.seminario.entitys;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hospitales")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "id_area")
	private Long idArea;
	
	@ManyToOne
	@JoinColumn(name = "id_area", nullable = false)
	private Area area;

	@OneToMany(mappedBy="hospital")
    private Set<Stock> stockByMedicamento;
	
	@OneToOne(mappedBy = "hospital")
	private User user;
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "direccion")
	private String direccion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public Set<Stock> getStockByMedicamento() {
		return stockByMedicamento;
	}

	public void setStockByMedicamento(Set<Stock> stockByMedicamento) {
		this.stockByMedicamento = stockByMedicamento;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
