package com.codigo.spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aerolineas")
public class AerolineaEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_aerolinea")
    private int id;
    private String nombre;

    public AerolineaEntity(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public AerolineaEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
