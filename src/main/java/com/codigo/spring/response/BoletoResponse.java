package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.SqlReturnType;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class BoletoResponse {
    private String nombre;
    private String apellido;
    private int asiento;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
}
