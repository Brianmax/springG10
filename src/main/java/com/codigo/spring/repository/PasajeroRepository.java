package com.codigo.spring.repository;

import com.codigo.spring.entity.PasajeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.IntrospectionException;

public interface PasajeroRepository extends JpaRepository<PasajeroEntity, Integer> {
}
