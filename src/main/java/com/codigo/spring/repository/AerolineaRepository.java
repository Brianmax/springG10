package com.codigo.spring.repository;

import com.codigo.spring.entity.AerolineaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AerolineaRepository extends JpaRepository<AerolineaEntity, Integer> {
}
