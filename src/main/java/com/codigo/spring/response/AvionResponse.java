package com.codigo.spring.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvionResponse extends AvionResponseBase{
    private int peso;
    private String aerolinea;
    public AvionResponse(int capacidad, String modelo, int peso, String aerolinea) {
        super(capacidad, modelo);
        this.peso = peso;
        this.aerolinea = aerolinea;
    }
}
