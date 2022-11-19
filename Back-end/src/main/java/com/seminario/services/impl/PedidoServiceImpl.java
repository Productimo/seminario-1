package com.seminario.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.MedicamentoRequestDTO;
import com.seminario.dtos.PedidoCargaResponseDTO;
import com.seminario.dtos.PedidoDTO;
import com.seminario.dtos.PedidoRequestDTO;
import com.seminario.dtos.PedidosResponseDto;
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
		pedido.setUrgencia(Boolean.FALSE);
		return pedidoRepository.save(pedido);
	}

	@Override
	public PedidoCargaResponseDTO cargaPedido(@Valid PedidoRequestDTO pedidoDto) {
		PedidoCargaResponseDTO response = new PedidoCargaResponseDTO();
		List<ResponseDTO> pedidos = new ArrayList<ResponseDTO>();
		for (MedicamentoRequestDTO medicamentoRequest : pedidoDto.getPedidos()) {
			pedidos.add(savePedido(medicamentoRequest, pedidoDto.getTipoEnvio(), pedidoDto.getIdHospital()));
		}
		response.setResponse(pedidos);
		return response;
	}
	
	private ResponseDTO savePedido (MedicamentoRequestDTO pedidoDto, String tipoPedido, Long idHospital) {
		
		Hospital hospital = hospitalRepository.getById(idHospital);
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
		pedido.setUrgencia("urgencia".equals(tipoPedido));
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

	@Override
	public PedidosResponseDto getPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		PedidosResponseDto response = new PedidosResponseDto();
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		for (Pedido pedido : pedidos) {
			PedidoDTO pedidoDto = new PedidoDTO();
			pedidoDto.setCantMedicamento(pedido.getStockReposicion().intValue());
			pedidoDto.setEstado(pedido.getEstado().getEstado());
			pedidoDto.setFechaPedido(pedido.getFechaPedido());
			pedidoDto.setHospital(pedido.getHospital().getNombre());
			pedidoDto.setIdPedido(pedido.getId());
			pedidoDto.setMedicamento(pedido.getMedicamento().getNombre());
			pedidoDto.setTipoPedido(pedido.getUrgencia() ? "urgencia" : "estandar");
			pedidosDTO.add(pedidoDto);
		}
		response.setPedidos(pedidosDTO);
		return response;
	}

	
}
