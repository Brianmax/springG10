package com.codigo.spring.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class VueloResponse {
    private Date fechaSalida;
    private Date fechaLlegada;
    private String origen;
    private String destino;

    private AvionResponseBase avion;
}
