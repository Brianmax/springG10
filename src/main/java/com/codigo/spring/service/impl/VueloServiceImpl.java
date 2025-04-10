package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.repository.AvionRepository;
import com.codigo.spring.repository.VueloRepository;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.response.AvionResponseBase;
import com.codigo.spring.response.VueloResponse;
import com.codigo.spring.service.VueloService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {
    private VueloRepository vueloRepository;
    private final AvionRepository avionRepository;

    public VueloServiceImpl(VueloRepository vueloRepository, AvionRepository avionRepository) {
        this.vueloRepository = vueloRepository;
        this.avionRepository = avionRepository;
    }

    @Override
    public VueloEntity save(VueloRequest vueloRequest) {
        Optional<AvionEntity> optionalAvionEntity = avionRepository.findById(vueloRequest.getAvionId());

        if(optionalAvionEntity.isEmpty()) {
            return null;
        }

        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setOrigen(vueloRequest.getOrigen());
        vueloEntity.setDestino(vueloRequest.getDestino());
        vueloEntity.setFechaLlegada(vueloRequest.getFechaLlegada());
        vueloEntity.setFechaSalida(vueloRequest.getFechaSalida());
        vueloEntity.setAvion(optionalAvionEntity.get());
        return vueloRepository.save(vueloEntity);

    }

    @Override
    public VueloResponse findById(int id) {
        Optional<VueloEntity> optionalVuelo = vueloRepository.findById(id);

        if(optionalVuelo.isEmpty()) {
            return null;
        }
        VueloEntity vueloEntity = optionalVuelo.get();
        VueloResponse vueloResponse = new VueloResponse();
        vueloResponse.setFechaSalida(vueloEntity.getFechaSalida());
        vueloResponse.setFechaLlegada(vueloEntity.getFechaLlegada());
        vueloResponse.setOrigen(vueloEntity.getOrigen());
        vueloResponse.setDestino(vueloEntity.getDestino());
        vueloResponse.setAvion(new AvionResponseBase(vueloEntity.getAvion().getCapacidad(), vueloEntity.getAvion().getModelo()));
        return vueloResponse;
    }
}
