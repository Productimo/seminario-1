package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
