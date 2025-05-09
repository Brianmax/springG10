package com.codigo.spring.service;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.response.AvionResponse;
import com.codigo.spring.response.ResponseBase;

import java.util.List;

public interface AvionService {
    AvionEntity save(AvionEntity avionEntity);
    List<AvionResponse> findAll();
    List<AvionResponse> findAllCapacidad(int min, int max);
    ResponseBase<AvionResponse> updateAerolinea(int idAvion, int idNuevaAerlinea);
}
