package com.codigo.spring.repository;

import com.codigo.spring.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface VueloRepository extends JpaRepository<VueloEntity, Integer> {

    @Query(value = "SELECT * FROM vuelos WHERE fecha_salida >= :fechaSalida ORDER BY(fecha_salida) ASC", nativeQuery = true)
    List<VueloEntity> findByFechaSalida(Date fechaSalida);
}
