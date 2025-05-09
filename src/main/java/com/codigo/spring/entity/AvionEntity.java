package com.codigo.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aviones")
@Getter
@Setter
public class AvionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion")
    private Long id;
    private int capacidad;
    private int peso;
    private String modelo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_aerolinea_fk")
    private AerolineaEntity aerolinea;
}
