package com.codigo.spring.service;

import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.response.VueloResponse;

public interface VueloService {
    VueloEntity save(VueloRequest vueloRequest);
    VueloResponse findById(int id);
}
