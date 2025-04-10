package com.codigo.spring.service;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.response.AvionResponse;

import java.util.List;

public interface AvionService {
    AvionEntity save(AvionEntity avionEntity);
    List<AvionResponse> findAll();
    List<AvionResponse> findAllCapacidad(int min, int max);
}
