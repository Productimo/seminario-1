package com.seminario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seminario.entitys.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	List<Stock> findByIdHospital(Long idHospital);
	
	Stock findByIdHospitalAndIdMedicamento(Long idHospital, Long idMedicamento);
	
	@Query(value = "SELECT * FROM stock u WHERE u.stock_real < u.stock_seguridad", nativeQuery=true)
	List<Stock> findStockWithStockUnderStockSeguridad();
	
	@Query(value = "SELECT * FROM stock u WHERE (u.stock_seguridad + u.stock_seguridad/4) > u.stock_real AND u.stock_seguridad < u.stock_real", nativeQuery=true)
	List<Stock> findStockWithStockCloseStockSeguridad();
	
	@Query(value = "SELECT * FROM stock u WHERE u.stock_real < u.stock_seguridad AND u.id_hospital = :hospital", nativeQuery=true)
	List<Stock> findStockWithStockUnderStockSeguridadAndByHospital(@Param("hospital") Long hospital);
	
	@Query(value = "SELECT * FROM stock u WHERE (u.stock_seguridad + u.stock_seguridad/4) > u.stock_real AND u.stock_seguridad < u.stock_real AND u.id_hospital = :hospital", nativeQuery=true)
	List<Stock> findStockWithStockCloseStockSeguridadAndByHosital(@Param("hospital") Long hospital);
}