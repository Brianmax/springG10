package com.codigo.spring.controller;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.repository.AvionRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/avion")
public class AvionController {
    private AvionRepository avionRepository;
    public AvionController(AvionRepository avionRepository) {
        this.avionRepository = avionRepository;
    }

    @PostMapping("/save")
    public AvionEntity save(@RequestBody AvionEntity avionEntity) {
        return avionRepository.save(avionEntity);
    }

}
