package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VueloResponse {
    private Date fechaSalida;
    private Date fechaLlegada;
    private String origen;
    private String destino;

    private AvionResponseBase avion;
    private List<String> pilotosVuelo;

    // Jose Ramirez ---> J. Ramirez
}
