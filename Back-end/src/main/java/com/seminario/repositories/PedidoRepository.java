package com.seminario.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seminario.entitys.Pedido;
import com.seminario.entitys.PedidosPorAnoBean;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByIdHospital(Long idHospital);
	
	@Query("SELECT new com.seminario.entitys.PedidosPorAnoBean(extract(month from p.fechaPedido), count(p.id)) " +
		"FROM Pedido p " +
		"where p.fechaPedido BETWEEN :desde and :hasta " +
		"GROUP BY extract(month from p.fechaPedido) " +
		"ORDER BY extract(month from p.fechaPedido) asc")
    List<PedidosPorAnoBean> pedidosPorAno(@Param("desde") Date desde, @Param("hasta") Date hasta);
	
	
	@Query("SELECT new com.seminario.entitys.PedidosPorAnoBean(extract(month from p.fechaPedido), count(p.id)) " +
			"FROM Pedido p " +
			"where p.fechaPedido BETWEEN :desde and :hasta " +
			"AND p.idHospital = :hospital " +
			"GROUP BY extract(month from p.fechaPedido) " +
			"ORDER BY extract(month from p.fechaPedido) asc")
	List<PedidosPorAnoBean> pedidosPorAnoByIdHospital(@Param("desde") Date desde, @Param("hasta") Date hasta, @Param("hospital") Long hospital);
}
