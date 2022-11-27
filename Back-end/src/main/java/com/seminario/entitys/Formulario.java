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
	
	@Column(name = "id_hospital", insertable = false,  updatable = false)
	private Long idHospital;
	
	@Column(name = "id_medico", insertable = false,  updatable = false)
	private Long idMedico;
	
	@Column(name = "id_medicamento", insertable = false,  updatable = false)
	private Long idMedicamento;
	
	@Column(name = "id_historial", insertable = false,  updatable = false)
	private Long idHistorial;
	
	@Column(name = "id_paciente", insertable = false,  updatable = false)
	private Long idPaciente;
	
	@ManyToOne
	@JoinColumn(name = "id_hospital", nullable = false)
	private Hospital hospital;
	
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;
	
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
	private Integer cantMedicamentoUsado;

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

	public Integer getCantMedicamentoUsado() {
		return cantMedicamentoUsado;
	}

	public void setCantMedicamentoUsado(Integer cantMedicamentoUsado) {
		this.cantMedicamentoUsado = cantMedicamentoUsado;
	}

	public Long getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public Long getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(Long idHistorial) {
		this.idHistorial = idHistorial;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
