package com.codigo.spring.service;

import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.request.VueloRequest;

public interface VueloService {
    VueloEntity save(VueloRequest vueloRequest);
    VueloEntity findById(Long id);
}
