package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

}
