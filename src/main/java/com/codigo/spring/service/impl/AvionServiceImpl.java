package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AerolineaEntity;
import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.mapper.AvionMapper;
import com.codigo.spring.repository.AerolineaRepository;
import com.codigo.spring.repository.AvionRepository;
import com.codigo.spring.response.AvionResponse;
import com.codigo.spring.response.AvionResponseBase;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.AvionService;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.ResourceTransactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvionServiceImpl implements AvionService {
    private final AvionRepository avionRepository;
    private final AerolineaRepository aerolineaRepository;

    public AvionServiceImpl(AvionRepository avionRepository, AerolineaRepository aerolineaRepository) {
        this.avionRepository = avionRepository;
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public AvionEntity save(AvionEntity avionEntity) {
        return null;
    }

    @Override
    public List<AvionResponse> findAll() {
        return List.of();
    }

    @Override
    public List<AvionResponse> findAllCapacidad(int min, int max) {
        List<AvionEntity> list = avionRepository.findByCapacidad(min, max);
        List<AvionResponse> responseList = new ArrayList<>();
        for(AvionEntity avionEntity : list){
            responseList.add(AvionMapper.INSTANCE.toAvionResponse(avionEntity, avionEntity.getAerolinea()));
        }
        return responseList;
    }

    @Override
    public ResponseBase<AvionResponse> updateAerolinea(int idAvion, int idNuevaAerlinea) {
        Optional<AvionEntity> avionEntityOptional = avionRepository.findById(idAvion);
        Optional<AerolineaEntity> aerolineaEntityOptional = aerolineaRepository.findById(idNuevaAerlinea);

        if(avionEntityOptional.isPresent() && aerolineaEntityOptional.isPresent()){
            AvionEntity avionEntity = avionEntityOptional.get();
            AerolineaEntity aerolineaEntity = aerolineaEntityOptional.get();

            avionEntity.setAerolinea(aerolineaEntity);
            avionRepository.save(avionEntity);

            return new ResponseBase<>(200, "Actulizacion correcta", Optional.of(fromAvionEntity(avionEntity)));
        }
        return new ResponseBase<>(404, "Datos incorrectos", Optional.empty());
    }

    private AvionResponse fromAvionEntity(AvionEntity avionEntity) {
        return new AvionResponse(
                avionEntity.getCapacidad(),
                avionEntity.getModelo(),
                avionEntity.getPeso(),
                avionEntity.getAerolinea().getNombre());
    }
}
