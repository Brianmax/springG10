package com.codigo.spring.controller;

import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.repository.PasajeroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pasajero")
public class PasajeroController {

    private PasajeroRepository pasajeroRepository;

    public PasajeroController(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
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
}
