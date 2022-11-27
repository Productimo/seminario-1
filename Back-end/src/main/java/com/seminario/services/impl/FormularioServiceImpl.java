package com.seminario.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.seminario.dtos.FormularioDTO;
import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.FormularioResponseDTO;
import com.seminario.dtos.MedicamentoUso;
import com.seminario.dtos.MedicamentoUsoResponseDTO;
import com.seminario.dtos.ResponseDTO;
import com.seminario.entitys.Formulario;
import com.seminario.entitys.Historial;
import com.seminario.entitys.Hospital;
import com.seminario.entitys.Medicamento;
import com.seminario.entitys.MedicamentoUsoBean;
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
import com.seminario.services.PacienteService;
import com.seminario.services.PedidoService;
import com.seminario.services.StockService;
import com.seminario.services.UserService;


@Service
public class FormularioServiceImpl implements FormularioService{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private FormularioRepository formularioRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private HistorialRepository historialRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

	@Override
	public ResponseDTO cargaFormulario(FormularioRequestDTO formularioDTO) {
		
		Optional<Hospital> hospitalOpt = hospitalRepository.findById(formularioDTO.getIdHospital());
		Hospital hospital = new Hospital();
		if (hospitalOpt.isEmpty()) {
			log.error("El id del hospital no coincide con ninguno existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "El id del hospital no coincide con ninguno existente en la base de datos");
		} else {
			hospital = hospitalOpt.get();
		}
		
		Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(formularioDTO.getIdMedicamento());
		Medicamento medicamento = new Medicamento();
		if (medicamentoOpt.isEmpty()) {
			log.error("El id del medicamento no coincide con ninguno existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "El id del medicamento no coincide con ninguno existente en la base de datos");
		} else {
			medicamento = medicamentoOpt.get();
		}
		
		Optional<Medico> medicoOpt = medicoRepository.findById(formularioDTO.getDniMedico());
		Medico medico = new Medico();
		if (medicoOpt.isEmpty()) {
			log.error("La matricula no coincide con ninguna existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "La matricula no coincide con ninguna existente en la base de datos");
		}else {
			medico = medicoOpt.get();
		}
		
		Optional<Paciente> pacienteOpt = pacienteRepository.findById(formularioDTO.getDniPaciente());
		Paciente paciente = new Paciente();
		if (pacienteOpt.isEmpty()) {
			log.info("Se genera nuevo paciente con DNI: " + formularioDTO.getDniPaciente());
			paciente = pacienteService.generateNewPaciente(formularioDTO);
		}else {
			paciente = pacienteOpt.get();
		}
		
		Stock stock = stockService.findByHospitalAndMedicamento(hospital.getId(), medicamento.getId());
		if (stock == null || stock.getId() == null) { 
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
		formulario.setMedico(medico);
		formulario.setPaciente(paciente);
		formulario.setIdPaciente(paciente.getDni());
		formularioRepository.saveAndFlush(formulario);
		
		
		Historial historial = new Historial();
		historial.setFechaCarga(new Date());
		historial.setStockSnapshot(stock.getStockReal());
		historial.setStock(stock);
		historial.setFormulario(formulario);
		
		stockService.updateStock(hospital.getId(), medicamento.getId(), formularioDTO.getCantAmpollas());
		
		if(stock.getStockReal() - formularioDTO.getCantAmpollas() < stock.getStockSeguridad()) {
			Pedido pedido = pedidoService.generateNewPedido(formularioDTO, hospital, medicamento, stock);
			historial.setPedido(pedido);
			historialRepository.saveAndFlush(historial);
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

	@Override
	public MedicamentoUsoResponseDTO fetchMedicamentoUso(String mes1, String mes2, Long idHospital) throws ParseException {		
		MedicamentoUsoResponseDTO response = new MedicamentoUsoResponseDTO();
		List<MedicamentoUso> usos = new ArrayList<MedicamentoUso>();
		if (idHospital != null) {
			List<MedicamentoUsoBean> usoMes1 = formularioRepository.findUsoMedicamentoEnFormulariosByHospital(modifyDate(mes1, 0), modifyDate(mes1, 1), idHospital);
			List<MedicamentoUsoBean> usoMes2 = formularioRepository.findUsoMedicamentoEnFormulariosByHospital(modifyDate(mes2, 0), modifyDate(mes2, 1), idHospital);
			usos = getComparativa(usoMes1, usoMes2);
		} else {
			List<MedicamentoUsoBean> usoMes1 = formularioRepository.findUsoMedicamentoEnFormularios(modifyDate(mes1, 0), modifyDate(mes1, 1));
			List<MedicamentoUsoBean> usoMes2 = formularioRepository.findUsoMedicamentoEnFormularios(modifyDate(mes2, 0), modifyDate(mes2, 1));
			usos = getComparativa(usoMes1, usoMes2);
		}
		response.setMedicamentos(usos);
		return response;
	}



	private List<MedicamentoUso> getComparativa(List<MedicamentoUsoBean> usoMes1, List<MedicamentoUsoBean> usoMes2) {
		List<MedicamentoUso> usos = new ArrayList<MedicamentoUso>();
		Boolean flag = Boolean.FALSE; 
		for (MedicamentoUsoBean uso1 : usoMes1) {
			for (MedicamentoUsoBean uso2 : usoMes2) {
				if(uso1.getIdMedicamento() == uso2.getIdMedicamento()) {
					MedicamentoUso comparativa = new MedicamentoUso();
					Medicamento medicamento = medicamentoRepository.getById(uso1.getIdMedicamento());
					comparativa.setName(medicamento != null ? medicamento.getNombre() : "");
					comparativa.setMes1(uso1.getTotal());
					comparativa.setMes2(uso2.getTotal());
					usos.add(comparativa);
					flag = Boolean.TRUE;
				}
			}
			if(!flag) {
				MedicamentoUso comparativa = new MedicamentoUso();
				Medicamento medicamento = medicamentoRepository.getById(uso1.getIdMedicamento());
				comparativa.setName(medicamento != null ? medicamento.getNombre() : "");
				comparativa.setMes1(uso1.getTotal());
				comparativa.setMes2(0L);
				usos.add(comparativa);
			} else {
				flag = Boolean.FALSE;
			}
		}
		
		for (MedicamentoUsoBean uso2 : usoMes2) {
			for (MedicamentoUsoBean uso1 : usoMes1) {
				if(uso1.getIdMedicamento() == uso2.getIdMedicamento()) {
					flag = Boolean.TRUE;
				}
			}
			if(!flag) {
				MedicamentoUso comparativa = new MedicamentoUso();
				Medicamento medicamento = medicamentoRepository.getById(uso2.getIdMedicamento());
				comparativa.setName(medicamento != null ? medicamento.getNombre() : "");
				comparativa.setMes1(0L);
				comparativa.setMes2(uso2.getTotal());
				usos.add(comparativa);
			} else {
				flag = Boolean.FALSE;
			}
		}
		return usos;
	}

	private Date modifyDate(String mes, Integer n) throws ParseException {
		Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(mes);
		fecha.setMonth(fecha.getMonth() + n);
		return fecha;
	}



	@Override
	public FormularioResponseDTO getFormularios(Long idHospital) {
		List<Formulario> formulariosEntity = new ArrayList<Formulario>();
		List<FormularioDTO> formulariosDTO = new ArrayList<FormularioDTO>();
		if(idHospital != null) {
			formulariosEntity = formularioRepository.findByIdHospital(idHospital);
		}else {
			formulariosEntity = formularioRepository.findAll();
		}
		for (Formulario entity : formulariosEntity) {
			FormularioDTO dto = new FormularioDTO();
			dto.setCantMedicamentoUsado(entity.getCantMedicamentoUsado());
			dto.setDescripcion(entity.getDescripcion());
			dto.setFechaAtencion(entity.getFechaAtencion().toString());
			dto.setHospital(entity.getHospital().getNombre());
			dto.setId(entity.getId());
			dto.setMedicamento(entity.getMedicamento().getNombre());
			dto.setMedico(entity.getMedico().getNombre() + " " + entity.getMedico().getApellido());
			dto.setMedicoMatricula(entity.getMedico().getMatricula());
			formulariosDTO.add(dto);
		}
		
		return new FormularioResponseDTO(formulariosDTO);
	}

}
