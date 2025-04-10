package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.repository.AvionRepository;
import com.codigo.spring.response.AvionResponse;
import com.codigo.spring.service.AvionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvionServiceImpl implements AvionService {
    private final AvionRepository avionRepository;

    public AvionServiceImpl(AvionRepository avionRepository) {
        this.avionRepository = avionRepository;
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
            responseList.add(new AvionResponse(
                    avionEntity.getCapacidad(),
                    avionEntity.getModelo(),
                    avionEntity.getPeso(),
                    avionEntity.getAerolinea().getNombre()
                    ));
        }
        return responseList;
    }
}
