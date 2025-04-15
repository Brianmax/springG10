package com.codigo.spring.repository;

import com.codigo.spring.entity.PilotoEntity;
import com.codigo.spring.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface VueloRepository extends JpaRepository<VueloEntity, Integer> {

    @Query(value = "SELECT * FROM vuelos WHERE fecha_salida >= :fechaSalida ORDER BY(fecha_salida) ASC", nativeQuery = true)
    List<VueloEntity> findByFechaSalida(Date fechaSalida);

    @Query(value = "SELECT p.id_piloto, p.nombre, p.apellido, p.estado,\n" +
            "\tp.fecha_creacion, p.fecha_modificacion, p.fecha_eliminacion\n" +
            "FROM vuelos v JOIN vuelo_piloto vp ON\n" +
            "v.id_vuelo = vp.id_vuelo_fk\n" +
            "JOIN pilotos p ON \n" +
            "vp.id_piloto_fk = p.id_piloto\n" +
            "WHERE v.id_vuelo = :idVuelo", nativeQuery = true)
    List<PilotoEntity> findPilotosByVuelo(int idVuelo);
}
