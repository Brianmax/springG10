package com.codigo.spring.service;

import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.request.VueloRequestUpdatePilotos;
import com.codigo.spring.response.VueloResponse;

import java.sql.Date;
import java.util.List;

public interface VueloService {
    VueloEntity save(VueloRequest vueloRequest);
    VueloResponse findById(int id);
    List<VueloResponse> findAllByFechaSalida(Date fechaSalida);
    VueloResponse addPiltosToVuelo(VueloRequestUpdatePilotos vueloRequestUpdatePilotos);
}
