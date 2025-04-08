package com.codigo.spring.repository;

import com.codigo.spring.entity.AvionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvionRepository extends JpaRepository<AvionEntity, Integer> {
}
