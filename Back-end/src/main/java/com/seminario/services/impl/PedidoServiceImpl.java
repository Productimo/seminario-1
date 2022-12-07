package com.seminario.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.seminario.dtos.FormularioRequestDTO;
import com.seminario.dtos.MedicamentoRequestDTO;
import com.seminario.dtos.PedidoCargaResponseDTO;
import com.seminario.dtos.PedidoDTO;
import com.seminario.dtos.PedidoPorAnoDTO;
import com.seminario.dtos.PedidoPorAnoResponseDTO;
import com.seminario.dtos.PedidoRequestDTO;
import com.seminario.dtos.PedidosResponseDto;
import com.seminario.dtos.ResponseDTO;
import com.seminario.entitys.EstadoPedido;
import com.seminario.entitys.Hospital;
import com.seminario.entitys.Medicamento;
import com.seminario.entitys.MedicamentoUsoBean;
import com.seminario.entitys.Pedido;
import com.seminario.entitys.PedidosPorAnoBean;
import com.seminario.entitys.Stock;
import com.seminario.repositories.EstadoPedidoRepository;
import com.seminario.repositories.HospitalRepository;
import com.seminario.repositories.MedicamentoRepository;
import com.seminario.repositories.PedidoRepository;
import com.seminario.services.PedidoService;
import com.seminario.services.StockService;
import com.seminario.services.UserService;

@Service
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private EstadoPedidoRepository estadoPedidoRepository;

	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

	@Override
	public Pedido generateNewPedido(FormularioRequestDTO formularioDTO, Hospital hospital, Medicamento medicamento, Stock stock) {
		Pedido pedido = new Pedido();
		EstadoPedido estado = estadoPedidoRepository.getById(1L);
		pedido.setIdEstado(1L);
		pedido.setEstado(estado);
		pedido.setFechaPedido(new Date());
		pedido.setIdHospital(hospital.getId());
		pedido.setHospital(hospital);
		pedido.setIdMedicamento(medicamento.getId());
		pedido.setMedicamento(medicamento);
		pedido.setStockReposicion((stock.getStockSeguridad() * 2) - stock.getStockReal());
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
		
		EstadoPedido estado = estadoPedidoRepository.getById(1L);
		Pedido pedido = new Pedido();
		pedido.setIdEstado(1L);
		pedido.setEstado(estado);
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
			if(estado.getId() == 3L) {
				stockService.updateStockPedido(pedido.getIdHospital(), pedido.getIdMedicamento(), pedido.getStockReposicion());
				return new ResponseDTO(HttpStatus.OK.value(), "Se modifico el estado del pedido a " + estado.getEstado() 
				+ ". Como se modifico al ultimo estado, el stock a reponser de este pedido se hizo efectivo");
			}
			return new ResponseDTO(HttpStatus.OK.value(), "Se modifico el estado del pedido a " + estado.getEstado());
		} catch (Exception e) {
			return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "No se puede actualizar el pedido. Error: " + e.getMessage());
		}
	}

	@Override
	public PedidosResponseDto getPedidos(Long idHospital) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		if (idHospital != null) {
			pedidos = pedidoRepository.findByIdHospital(idHospital);
		}else {
			pedidos = pedidoRepository.findAll();
		}

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

	@Override
	public PedidoPorAnoResponseDTO getPedidosPorAno(Long idHospital, Long year1, Long year2) throws ParseException {
		List<PedidoPorAnoDTO> responseList = new ArrayList<PedidoPorAnoDTO>();
		List<PedidosPorAnoBean> list1 = new ArrayList<PedidosPorAnoBean>();
		List<PedidosPorAnoBean> list2 = new ArrayList<PedidosPorAnoBean>();
		
		if(idHospital != null) {
			list1 = pedidoRepository.pedidosPorAnoByIdHospital(modifyFecha(year1, 0), modifyFecha(year1, 1), idHospital);
			list2 = pedidoRepository.pedidosPorAnoByIdHospital(modifyFecha(year2, 0), modifyFecha(year2, 1), idHospital);
		}else {
			list1 = pedidoRepository.pedidosPorAno(modifyFecha(year1, 0), modifyFecha(year1, 1));
			list2 = pedidoRepository.pedidosPorAno(modifyFecha(year2, 0), modifyFecha(year2, 1));
		}
		
		String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
		PedidoPorAnoDTO dto = new PedidoPorAnoDTO();
		for (String mes : meses) {
			switch (mes) {
			case "enero":
				dto = new PedidoPorAnoDTO("enero", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 1) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 1) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "febrero":
				dto = new PedidoPorAnoDTO("febrero", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 2) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 2) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "marzo":
				dto = new PedidoPorAnoDTO("marzo", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 3) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 3) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "abril":
				dto = new PedidoPorAnoDTO("abril", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 4) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 4) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "mayo":
				dto = new PedidoPorAnoDTO("mayo", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 5) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 5) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "junio":
				dto = new PedidoPorAnoDTO("junio", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 6) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 6) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "julio":
				dto = new PedidoPorAnoDTO("julio", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 7) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 7) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "agosto":
				dto = new PedidoPorAnoDTO("agosto", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 8) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 8) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "septiembre":
				dto = new PedidoPorAnoDTO("septiembre", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 9) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 9) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "octubre":
				dto = new PedidoPorAnoDTO("octubre", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 10) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 10) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "noviembre":
				dto = new PedidoPorAnoDTO("noviembre", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 11) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 11) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			case "diciembre":
				dto = new PedidoPorAnoDTO("diciembre", 0L, 0L);
				for (PedidosPorAnoBean aux : list1) {
					if(aux.getMes() == 12) {
						dto.setTotal1(aux.getTotal());
					}
				}
				for (PedidosPorAnoBean aux : list2) {
					if(aux.getMes() == 12) {
						dto.setTotal2(aux.getTotal());
					}
				}
				responseList.add(dto);
				break;
			default:
				break;
			}
		}
		
		
		return new PedidoPorAnoResponseDTO(responseList);
	}

	private Date modifyFecha(Long year, int n) throws ParseException {
		StringBuilder stringBuilder = new StringBuilder();
	    stringBuilder.append("01/01/").append(year.toString());
	    String sf  = stringBuilder.toString();
	    
		Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(sf);
		fecha.setYear(fecha.getYear() + n);
		return fecha;
	}
	
}
