package com.codigo.spring.repository;

import com.codigo.spring.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<VueloEntity, Integer> {
}
