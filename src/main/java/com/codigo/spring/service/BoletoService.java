package com.codigo.spring.service;

import com.codigo.spring.request.BoletoRequest;
import com.codigo.spring.response.BoletoResponse;

public interface BoletoService {
    BoletoResponse save(BoletoRequest boletoRequest);
    BoletoResponse findById(int idVuelo);
}
