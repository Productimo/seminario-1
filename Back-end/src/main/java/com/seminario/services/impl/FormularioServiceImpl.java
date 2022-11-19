package com.seminario.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.ResponseDTO;
import com.seminario.entitys.Formulario;
import com.seminario.entitys.Historial;
import com.seminario.entitys.Hospital;
import com.seminario.entitys.Medicamento;
import com.seminario.entitys.Medico;
import com.seminario.entitys.Paciente;
import com.seminario.entitys.Pedido;
import com.seminario.entitys.Stock;
import com.seminario.repositories.FormularioRepository;
import com.seminario.repositories.HistorialRepository;
import com.seminario.repositories.HospitalRepository;
import com.seminario.repositories.MedicamentoRepository;
import com.seminario.repositories.MedicoRepository;
import com.seminario.repositories.PacienteRepository;
import com.seminario.services.FormularioService;
import com.seminario.services.PedidoService;
import com.seminario.services.StockService;
import com.seminario.services.UserService;


@Service
public class FormularioServiceImpl implements FormularioService{

	private PacienteRepository pacienteRepository;
	private HospitalRepository hospitalRepository;
	private MedicamentoRepository medicamentoRepository;
	private MedicoRepository medicoRepository;
	private StockService stockService;
	private FormularioRepository formularioRepository;
	private PedidoService pedidoService;
	private HistorialRepository historialRepository;
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	public FormularioServiceImpl(PacienteRepository pacienteRepository, HospitalRepository hospitalRepository,
			MedicamentoRepository medicamentoRepository,
			MedicoRepository medicoRepository, StockService stockService,
			FormularioRepository formularioRepository,
			PedidoService pedidoService,
			HistorialRepository historialRepository) {
		super();
		this.pacienteRepository = pacienteRepository;
		this.hospitalRepository = hospitalRepository;
		this.medicamentoRepository = medicamentoRepository;
		this.medicoRepository = medicoRepository;
		this.stockService = stockService;
		this.formularioRepository = formularioRepository;
		this.pedidoService = pedidoService;
		this.historialRepository = historialRepository;
	}



	@Override
	public ResponseDTO cargaFormulario(FormularioRequestDTO formularioDTO) {
		
		Hospital hospital = hospitalRepository.getById(formularioDTO.getIdHospital());
		if (hospital == null) {
			log.error("El id del hospital no coincide con ninguno existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "El id del hospital no coincide con ninguno existente en la base de datos");
		}
		
		Medicamento medicamento = medicamentoRepository.getById(formularioDTO.getIdMedicamento());
		if (medicamento == null) {
			log.error("El id del medicamento no coincide con ninguno existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "El id del medicamento no coincide con ninguno existente en la base de datos");
		}
		
		List<Medico> medicos = medicoRepository.findByMatricula(formularioDTO.getMatricula());
		Medico medico = medicos != null ? medicos.get(0) : null;
		if (medico == null) {
			log.error("La matricula no coincide con ninguna existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "La matricula no coincide con ninguna existente en la base de datos");
		}
		
		Paciente paciente = pacienteRepository.getById(formularioDTO.getDniPaciente());
		if (paciente == null) {
			log.info("Se genera nuevo paciente con DNI: " + formularioDTO.getDniPaciente());
			genereateNewPaciente(formularioDTO);
		}
		
		Stock stock = stockService.findByHospitalAndMedicamento(hospital.getId(), medicamento.getId());
		if (stock == null) { 
			log.error("No existe stock para esta combinacion de medicamento y hospital");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "No existe stock para esta combinacion de medicamento y hospital");
		}
		if (stock.getStockReal() - formularioDTO.getCantAmpollas() < 0) { 
			log.error("Se gastaron mas unidades de medicamento de las que existen en stock");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "Se gastaron mas unidades de medicamento de las que existen en stock");
		}
		
		
		
		Formulario formulario = new Formulario();
		formulario.setCantMedicamentoUsado(formularioDTO.getCantAmpollas());
		formulario.setDescripcion(formularioDTO.getComentarios());
		formulario.setFechaAtencion(formularioDTO.getFechaAtencion());
		formulario.setIdHospital(hospital.getId());
		formulario.setHospital(hospital);
		formulario.setIdMedicamento(medicamento.getId());
		formulario.setMedicamento(medicamento);
		formulario.setIdMedico(medico.getDni());		
		formularioRepository.save(formulario);
		
		
		Historial historial = new Historial();
		historial.setFechaCarga(new Date());
		historial.setStockSnapshot(stock.getStockReal());
		historial.setStock(stock);
		historial.setFormulario(formulario);
		
		stockService.updateStock(hospital.getId(), medicamento.getId(), formularioDTO.getCantAmpollas());
		
		if(stock.getStockReal() - formularioDTO.getCantAmpollas() < stock.getStockSeguridad()) {
			Pedido pedido = pedidoService.generateNewPedido(formularioDTO, hospital, medicamento, stock);
			historial.setPedido(pedido);
			historialRepository.save(historial);
			formulario.setHistorial(historial);
			formularioRepository.save(formulario);
			return new ResponseDTO(HttpStatus.OK.value(), "Se realizo la carga exitosa de la atencion del paciente y adicionalmente "
					+ "se genero el pedido #" + pedido.getId());
		}
		
		historialRepository.save(historial);
		formulario.setHistorial(historial);
		formularioRepository.save(formulario);
		
		return new ResponseDTO(HttpStatus.OK.value(), "Se realizo la carga exitosa de la atencion del paciente");
	}



	private void genereateNewPaciente(FormularioRequestDTO formulario) {
		Paciente paciente = new Paciente();
		paciente.setNombre(formulario.getNombrePaciente());
		paciente.setDni(formulario.getDniPaciente());
		paciente.setGenero(formulario.getGeneroPaciente());
		paciente.setEdad(formulario.getEdadPaciente());
		
		try {
			pacienteRepository.save(paciente);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
