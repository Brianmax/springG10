package com.codigo.spring.mapper;

import com.codigo.spring.entity.AerolineaEntity;
import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.response.AvionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvionMapper {

    AvionMapper INSTANCE = Mappers.getMapper(AvionMapper.class);

    @Mappings(value = {
            @Mapping(target = "capacidad", source = "avionEntity.capacidad"),
            @Mapping(target = "modelo", source = "avionEntity.modelo"),
            @Mapping(target = "peso", source = "avionEntity.peso"),
            @Mapping(target = "aerolinea", source = "aerolinea.nombre")
    })
    AvionResponse toAvionResponse(AvionEntity avionEntity, AerolineaEntity aerolinea);
}
