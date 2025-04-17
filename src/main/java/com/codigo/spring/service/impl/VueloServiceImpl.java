package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.entity.PilotoEntity;
import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.repository.AvionRepository;
import com.codigo.spring.repository.PilotoRepository;
import com.codigo.spring.repository.VueloRepository;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.request.VueloRequestUpdatePilotos;
import com.codigo.spring.response.AvionResponseBase;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.response.VueloResponse;
import com.codigo.spring.service.VueloService;
import com.codigo.spring.utils.Constants;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {
    private VueloRepository vueloRepository;
    private final AvionRepository avionRepository;
    private final PilotoRepository pilotoRepository;

    public VueloServiceImpl(VueloRepository vueloRepository, AvionRepository avionRepository, PilotoRepository pilotoRepository) {
        this.vueloRepository = vueloRepository;
        this.avionRepository = avionRepository;
        this.pilotoRepository = pilotoRepository;
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

    @Override
    public List<VueloResponse> findAllByFechaSalida(Date fechaSalida) {
        List<VueloEntity> vueloEntities = vueloRepository.findByFechaSalida(fechaSalida);
        List<VueloResponse> vueloResponses = new ArrayList<>();

        for(VueloEntity vueloEntity : vueloEntities) {
            vueloResponses.add(getVueloResponse(vueloEntity));
        }
        return vueloResponses;
    }

    @Override
    public ResponseBase<VueloResponse> addPiltosToVuelo(VueloRequestUpdatePilotos vueloRequestUpdatePilotos) {
        // List<PilotoEntity> pilotosVuelo = vueloRepository.findPilotosByVuelo(vueloRequestUpdatePilotos.getIdVuelo());
        List<PilotoEntity> nuevosPilotos = pilotoRepository.findPilotosByIds(vueloRequestUpdatePilotos.getIdsPilotos());
        Optional<VueloEntity> vueloEntityOptional = vueloRepository.findById(vueloRequestUpdatePilotos.getIdVuelo());

        if(vueloEntityOptional.isEmpty() || nuevosPilotos.size() != vueloRequestUpdatePilotos.getIdsPilotos().size()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        VueloEntity vueloEntity = vueloEntityOptional.get();
        List<PilotoEntity> pilotosVuelo = vueloEntity.getPilotos();

        for (PilotoEntity piloto : nuevosPilotos) {
            if (!pilotosVuelo.contains(piloto)) {
                pilotosVuelo.add(piloto);
                piloto.getVuelos().add(vueloEntity);
            }
        }

        // pilotosVuelo.addAll(nuevosPilotos);
        //vueloEntity.setPilotos(pilotosVuelo);
        vueloRepository.save(vueloEntity);

        VueloResponse vueloResponse = new VueloResponse(
                vueloEntity.getFechaSalida(),
                vueloEntity.getFechaLlegada(),
                vueloEntity.getOrigen(),
                vueloEntity.getDestino(),
                new AvionResponseBase(vueloEntity.getAvion().getCapacidad(), vueloEntity.getAvion().getModelo()),
                processPilotos(pilotosVuelo)
        );
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, Optional.of(vueloResponse));
    }

    @Override
    public ResponseBase<VueloResponse> removePilotosFromVuelo(VueloRequestUpdatePilotos vueloRequestUpdatePilotos) {
        Optional<VueloEntity> vueloEntityOptional = vueloRepository.findById(vueloRequestUpdatePilotos.getIdVuelo());
        List<PilotoEntity> pilotosEliminar = pilotoRepository.findPilotosByIds(vueloRequestUpdatePilotos.getIdsPilotos());

        if(vueloEntityOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        VueloEntity vueloEntity = vueloEntityOptional.get();
        List<PilotoEntity> pilotosVuelo = vueloEntity.getPilotos();

        for(PilotoEntity piloto : pilotosEliminar) {
            pilotosVuelo.remove(piloto);
            // eliminar el vuelo relacionado al piloto
            piloto.getVuelos().remove(vueloEntity);
        }

        vueloEntity.setPilotos(pilotosVuelo);
        vueloRepository.save(vueloEntity);
        VueloResponse vueloResponse = new VueloResponse(
                vueloEntity.getFechaSalida(),
                vueloEntity.getFechaLlegada(),
                vueloEntity.getOrigen(),
                vueloEntity.getDestino(),
                new AvionResponseBase(vueloEntity.getAvion().getCapacidad(), vueloEntity.getAvion().getModelo()),
                processPilotos(pilotosVuelo)
        );
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, Optional.of(vueloResponse));
    }

     private List<String> processPilotos(List<PilotoEntity> pilotoEntities) {
        List<String> pilotos = new ArrayList<>();
        for(PilotoEntity pilotoEntity : pilotoEntities) {
            pilotos.add(pilotoEntity.getNombre().charAt(0) + ". " + pilotoEntity.getApellido());
        }
        return pilotos;
    }

    private VueloResponse getVueloResponse(VueloEntity vueloEntity) {
        VueloResponse vueloResponse = new VueloResponse();
        vueloResponse.setOrigen(vueloEntity.getOrigen());
        vueloResponse.setDestino(vueloEntity.getDestino());
        vueloResponse.setFechaSalida(vueloEntity.getFechaSalida());
        vueloResponse.setFechaLlegada(vueloEntity.getFechaLlegada());
        vueloResponse.setAvion(new AvionResponseBase(
                vueloEntity.getAvion().getCapacidad(),
                vueloEntity.getAvion().getModelo()
        ));
        return vueloResponse;
    }
}
