package com.codigo.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.springframework.context.annotation.EnableMBeanExport;

@Getter
@Setter
@Entity
@Table(name = "boletos")
public class BoletosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleto")
    private int id;
    private int asiento;

    @ManyToOne
    @JoinColumn(name = "id_pasajero_fk")
    private PasajeroEntity pasajero;

    @ManyToOne
    @JoinColumn(name = "id_vuelo_fk")
    private VueloEntity vuelo;

    // asiento,
    // idPasajero,
    // idVuelo
}
