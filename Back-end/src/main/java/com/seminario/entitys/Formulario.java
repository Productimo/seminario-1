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
@Table(name = "formularios")
public class Formulario {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_hospital", nullable = false)
	private Hospital hospital;
	
	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false)
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "id_medicamento", nullable = false)
	private Medicamento medicamento;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_historial", referencedColumnName = "id")
    private Historial historial;
	
	@Column(name = "fecha_atencion")
	private Date fechaAtencion;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "cantidad_medicamentos")
	private Long cantMedicamentoUsado;

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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCantMedicamentoUsado() {
		return cantMedicamentoUsado;
	}

	public void setCantMedicamentoUsado(Long cantMedicamentoUsado) {
		this.cantMedicamentoUsado = cantMedicamentoUsado;
	}
	
}
