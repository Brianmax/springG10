package com.codigo.spring.controller;

import com.codigo.spring.entity.AerolineaEntity;
import com.codigo.spring.repository.AerolineaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaController {
    private AerolineaRepository aerolineaRepository;

    public AerolineaController(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @PostMapping("/save")
    public AerolineaEntity save(@RequestBody AerolineaEntity aerolineaEntity) {
        AerolineaEntity savedEntity = aerolineaRepository.save(aerolineaEntity);
        return aerolineaRepository.save(savedEntity);
    }
}
