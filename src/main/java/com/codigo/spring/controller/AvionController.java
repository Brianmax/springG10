package com.codigo.spring.controller;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.repository.AvionRepository;
import com.codigo.spring.response.AvionResponse;
import com.codigo.spring.service.AvionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avion")
public class AvionController {
    private AvionRepository avionRepository;
    private final AvionService avionService;
    public AvionController(AvionRepository avionRepository, AvionService avionService) {
        this.avionRepository = avionRepository;
        this.avionService = avionService;
    }

    @PostMapping("/save")
    public AvionEntity save(@RequestBody AvionEntity avionEntity) {
        return avionRepository.save(avionEntity);
    }

    @GetMapping("/find/modelo")
    public List<AvionEntity> findAll(@RequestParam String modelo) {
        List<AvionEntity> avionEntities1 = avionRepository.findByModelo(modelo);
        List<AvionEntity> avionEntities2 = avionRepository.customFindByModelo(modelo);
        return avionEntities1;
    }

    @GetMapping("/find/capacidad")
    public List<AvionResponse> findAllCapacidad(@RequestParam int min, @RequestParam int max) {
        return avionService.findAllCapacidad(min, max);
    }

}
