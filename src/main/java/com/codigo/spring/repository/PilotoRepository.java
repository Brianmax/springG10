package com.codigo.spring.repository;

import com.codigo.spring.entity.PilotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PilotoRepository extends JpaRepository<PilotoEntity, Integer> {

    @Query(value = "SELECT * FROM pilotos WHERE id_piloto IN :ids;", nativeQuery = true)
    List<PilotoEntity> findPilotosByIds(List<Integer> ids);
}
