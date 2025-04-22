package com.codigo.spring.repository;

import com.codigo.spring.entity.BoletosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoletoRepository extends JpaRepository<BoletosEntity, Integer> {
    @Query(value = "SELECT * FROM boletos WHERE id_pasajero_fk=:idPasajero;", nativeQuery = true)
    List<BoletosEntity> findBoletosByPasajero(int idPasajero);

    @Query(value = "SELECT * FROM boletos WHERE id_vuelo_fk = :idVuelo", nativeQuery = true)
    List<BoletosEntity> findBoletosByVuelo(int idVuelo);
}
