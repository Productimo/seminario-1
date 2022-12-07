package com.seminario.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seminario.entitys.Formulario;
import com.seminario.entitys.MedicamentoUsoBean;
import com.seminario.entitys.PedidosPorAnoBean;

public interface FormularioRepository extends JpaRepository<Formulario, Long> {

	List<Formulario> findByIdHospital(Long idHospital);
	
	@Query("SELECT new com.seminario.entitys.MedicamentoUsoBean(sum(f.cantMedicamentoUsado), f.idMedicamento) " +
	           "FROM Formulario f  WHERE f.fechaAtencion BETWEEN :desde and :hasta " +
	           "GROUP BY f.idMedicamento")
	    List<MedicamentoUsoBean> findUsoMedicamentoEnFormularios(@Param("desde") Date desde, @Param("hasta") Date hasta);
	
	@Query("SELECT new com.seminario.entitys.MedicamentoUsoBean(sum(f.cantMedicamentoUsado), f.idHospital, f.idMedicamento) " +
	           "FROM Formulario f " +
	           "WHERE f.idHospital = :idHospital AND f.fechaAtencion BETWEEN :desde and :hasta " +
	           "GROUP BY f.idMedicamento, f.idHospital")
	    List<MedicamentoUsoBean> findUsoMedicamentoEnFormulariosByHospital(@Param("desde") Date desde, @Param("hasta") Date hasta, @Param("idHospital") Long idHospital);


	@Query("SELECT new com.seminario.entitys.MedicamentoUsoBean(count( DISTINCT f.idPaciente), f.idHospital) FROM Formulario f " +
			"group by f.idHospital")
	List<MedicamentoUsoBean> pacienteGroupByHospital();
}
