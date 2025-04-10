package com.codigo.spring.repository;

import com.codigo.spring.entity.AvionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvionRepository extends JpaRepository<AvionEntity, Integer> {
    @Query(value = "SELECT * FROM aviones WHERE modelo= :modelo", nativeQuery = true)
    List<AvionEntity> customFindByModelo(String modelo);
    List<AvionEntity> findByModelo(String modelo);
    @Query(value = "SELECT * FROM aviones WHERE capacidad > :min AND capacidad < :max", nativeQuery = true)
    List<AvionEntity> findByCapacidad(int min, int max);
    List<AvionEntity> findByCapacidadBetween(int min, int max);

}
