package com.seminario.services.impl;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.PedidoRequestDTO;
import com.seminario.dtos.ResponseDTO;
import com.seminario.entitys.EstadoPedido;
import com.seminario.entitys.Hospital;
import com.seminario.entitys.Medicamento;
import com.seminario.entitys.Pedido;
import com.seminario.entitys.Stock;
import com.seminario.repositories.EstadoPedidoRepository;
import com.seminario.repositories.HospitalRepository;
import com.seminario.repositories.MedicamentoRepository;
import com.seminario.repositories.PedidoRepository;
import com.seminario.services.PedidoService;
import com.seminario.services.StockService;
import com.seminario.services.UserService;

public class PedidoServiceImpl implements PedidoService{

	private PedidoRepository pedidoRepository;
	private HospitalRepository hospitalRepository;
	private MedicamentoRepository medicamentoRepository;
	private StockService stockService;
	private EstadoPedidoRepository estadoPedidoRepository;

	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	public PedidoServiceImpl(PedidoRepository pedidoRepository, HospitalRepository hospitalRepository, MedicamentoRepository medicamentoRepository
			, StockService stockService, EstadoPedidoRepository estadoPedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
		this.hospitalRepository = hospitalRepository;
		this.medicamentoRepository = medicamentoRepository;
		this.stockService = stockService;
		this.estadoPedidoRepository = estadoPedidoRepository;
	}

	@Override
	public Pedido generateNewPedido(FormularioRequestDTO formularioDTO, Hospital hospital, Medicamento medicamento, Stock stock) {
		Pedido pedido = new Pedido();
		pedido.setIdEstado(1L);
		pedido.setFechaPedido(new Date());
		pedido.setIdHospital(hospital.getId());
		pedido.setHospital(hospital);
		pedido.setIdMedicamento(medicamento.getId());
		pedido.setMedicamento(medicamento);
		pedido.setStockReposicion((stock.getStockSeguridad() * 2) - (stock.getStockReal() - formularioDTO.getCantAmpollas()));
		pedido.setComentario("Pedido generado de forma automatica por el sistema");
		pedido.setUrgencia(Boolean.FALSE);
		return pedidoRepository.save(pedido);
	}

	@Override
	public ResponseDTO cargaFormulario(@Valid PedidoRequestDTO pedidoDto) {

		Hospital hospital = hospitalRepository.getById(pedidoDto.getIdHospital());
		if (hospital == null) {
			log.error("El id del hospital no coincide con ninguno existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "El id del hospital no coincide con ninguno existente en la base de datos");
		}
		
		Medicamento medicamento = medicamentoRepository.getById(pedidoDto.getIdMedicamento());
		if (medicamento == null) {
			log.error("El id del medicamento no coincide con ninguno existente en la base de datos");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "El id del medicamento no coincide con ninguno existente en la base de datos");
		}
		
		Stock stock = stockService.findByHospitalAndMedicamento(hospital.getId(), medicamento.getId());
		if (stock == null) { 
			log.error("No existe stock para esta combinacion de medicamento y hospital");
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "No existe stock para esta combinacion de medicamento y hospital");
		}
		
		Pedido pedido = new Pedido();
		pedido.setIdEstado(1L);
		pedido.setFechaPedido(new Date());
		pedido.setIdHospital(hospital.getId());
		pedido.setHospital(hospital);
		pedido.setIdMedicamento(medicamento.getId());
		pedido.setMedicamento(medicamento);
		pedido.setStockReposicion(pedidoDto.getCantMedicamento().longValue());
		pedido.setComentario(pedidoDto.getComentarios());
		pedido.setUrgencia("urgencia".equals(pedidoDto.getTipoEnvio()));
		pedido = pedidoRepository.save(pedido);
		
		return new ResponseDTO(HttpStatus.OK.value(), "Se realizo la carga de tu pedido #" + pedido.getId());

	}

	@Override
	public ResponseDTO updateEstadoPedido(Long idPedido) {
		Pedido pedido;
		try {
			pedido = pedidoRepository.getById(idPedido);
			EstadoPedido estado = pedido.getEstado();
			if(estado.getId() == 3L) {
				return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), "No se puede actualizar el pedido ya que se encuentra en el Estado: " + estado.getEstado());
			}
			estado = estadoPedidoRepository.getById(estado.getId() + 1);
			pedido.setEstado(estado);
			pedido.setIdEstado(estado.getId());
			pedidoRepository.save(pedido);
			return new ResponseDTO(HttpStatus.OK.value(), "Se modifico el estado del pedido a " + estado.getEstado());
		} catch (Exception e) {
			return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "No se puede actualizar el pedido. Error: " + e.getMessage());
		}
	}

	
}
