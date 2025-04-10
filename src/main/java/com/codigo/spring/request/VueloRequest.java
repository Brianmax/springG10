package com.codigo.spring.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class VueloRequest {
    private Date fechaSalida;
    private Date fechaLlegada;
    private String origen;
    private String destino;
    private int avionId;
}
