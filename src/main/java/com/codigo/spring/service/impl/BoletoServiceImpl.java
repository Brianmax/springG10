package com.codigo.spring.service.impl;

import com.codigo.spring.entity.BoletosEntity;
import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.repository.BoletoRepository;
import com.codigo.spring.repository.PasajeroRepository;
import com.codigo.spring.repository.VueloRepository;
import com.codigo.spring.request.BoletoRequest;
import com.codigo.spring.response.BoletoResponse;
import com.codigo.spring.service.BoletoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletoServiceImpl implements BoletoService {
    private final BoletoRepository boletoRepository;
    private final VueloRepository vueloRepository;
    private final PasajeroRepository pasajeroRepository;

    public BoletoServiceImpl(BoletoRepository boletoRepository, VueloRepository vueloRepository, PasajeroRepository pasajeroRepository) {
        this.boletoRepository = boletoRepository;
        this.vueloRepository = vueloRepository;
        this.pasajeroRepository = pasajeroRepository;
    }

    @Override
    public BoletoResponse save(BoletoRequest boletoRequest) {
        VueloEntity vuelo = vueloRepository.findById(boletoRequest.getIdVuelo()).orElse(null);
        PasajeroEntity pasajero = pasajeroRepository.findById(boletoRequest.getIdClient()).orElse(null);
        if (vuelo == null || pasajero == null) {
            return null;
        }
        List<BoletosEntity> boletosByVuelo = boletoRepository.findBoletosByVuelo(boletoRequest.getIdVuelo());

        for(BoletosEntity boleto: boletosByVuelo) {
            if(boletoRequest.getAsiento() == boleto.getAsiento()) {
                return null;
            }
        }

        BoletosEntity boleto = new BoletosEntity();
        boleto.setAsiento(boletoRequest.getAsiento());
        boleto.setVuelo(vuelo);
        boleto.setPasajero(pasajero);
        boletoRepository.save(boleto);
        return new BoletoResponse(
                pasajero.getNombre(),
                pasajero.getApellido(),
                boleto.getAsiento(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                vuelo.getFechaSalida(),
                vuelo.getFechaLlegada());
    }

    @Override
    public BoletoResponse findById(int idVuelo) {
        return null;
    }
}
