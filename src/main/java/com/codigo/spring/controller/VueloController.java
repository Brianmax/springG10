package com.codigo.spring.controller;

import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.response.VueloResponse;
import com.codigo.spring.service.VueloService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController {

    private VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping("/save")
    public VueloEntity save(@RequestBody VueloRequest vueloRequest) {
        return vueloService.save(vueloRequest);
    }

    @GetMapping("/find/{id}")
    public VueloResponse findById(@PathVariable int id) {
        return vueloService.findById(id);
    }
}
