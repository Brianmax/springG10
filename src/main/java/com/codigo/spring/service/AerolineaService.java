package com.codigo.spring.service;

import com.codigo.spring.entity.AerolineaEntity;

import java.util.List;

public interface AerolineaService {

    AerolineaEntity save(AerolineaEntity aerolinea);
    List<AerolineaEntity> findAll();
    AerolineaEntity findById(int id);
    AerolineaEntity updateById(AerolineaEntity aerolinea);
}
