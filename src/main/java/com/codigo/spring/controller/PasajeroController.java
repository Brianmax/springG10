package com.codigo.spring.controller;

import com.codigo.spring.entity.BoletosEntity;
import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.repository.BoletoRepository;
import com.codigo.spring.repository.PasajeroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pasajero")
public class PasajeroController {

    private PasajeroRepository pasajeroRepository;
    private final BoletoRepository boletoRepository;

    public PasajeroController(PasajeroRepository pasajeroRepository, BoletoRepository boletoRepository) {
        this.pasajeroRepository = pasajeroRepository;
        this.boletoRepository = boletoRepository;
    }

    @PostMapping("/save")
    public PasajeroEntity save(@RequestBody PasajeroEntity pasajero) {
        PasajeroEntity savedPasajero = pasajeroRepository.save(pasajero);
        return savedPasajero;
    }

    @GetMapping("/find/{id}")
    public PasajeroEntity findById(@PathVariable int id) {
        Optional<PasajeroEntity> optionalPasajero = pasajeroRepository.findById(id);

        if(optionalPasajero.isEmpty()) {
            return null;
        }
        return optionalPasajero.get();
    }

    // implementar la busqueda de un pasajero por id
    // el endpoint debe de traer la siguiente informacion
    // nombre, apellido, asiento, origen, destino

    @GetMapping("/find/boleto/{id}")
    public PasajeroEntity findBoleto(@PathVariable int id) {
        List<BoletosEntity> boletos = boletoRepository.findBoletosByPasajero(id);
        if(boletos.isEmpty()) {
            return null;
        }
        return null;
    }
}
