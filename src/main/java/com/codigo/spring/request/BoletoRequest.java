package com.codigo.spring.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoRequest {
    private int idVuelo;
    private int idClient;
    private int asiento;
}
