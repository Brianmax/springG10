package com.codigo.spring.response;

import com.codigo.spring.entity.BoletosEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PasajeroInfoResponse {
    private String nombre;
    private String apellido;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoletoInfo {
        private String origen;
        private String destino;
        private int asiento;
    }
    private List<BoletoInfo> boletos;
}
