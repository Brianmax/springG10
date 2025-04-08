package com.codigo.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pasajeros")
@Getter
@Setter
public class PasajeroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    private int id;
    private String nombre;
    private String apellido;
}
