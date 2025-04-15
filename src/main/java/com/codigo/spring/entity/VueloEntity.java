package com.codigo.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vuelos")
public class VueloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private int id;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String origen;
    private String destino;

    @ManyToOne
    @JoinColumn(name = "id_avion_fk")
    private AvionEntity avion;

    @ManyToMany(mappedBy = "vuelos")
    private List<PilotoEntity> pilotos;
}
