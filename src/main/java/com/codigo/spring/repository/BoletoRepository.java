package com.codigo.spring.repository;

import com.codigo.spring.entity.BoletosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<BoletosEntity, Integer> {
}
