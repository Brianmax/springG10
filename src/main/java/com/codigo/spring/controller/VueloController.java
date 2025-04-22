package com.codigo.spring.controller;

import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.request.VueloRequestUpdatePilotos;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.response.VueloResponse;
import com.codigo.spring.service.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelo")
@Tag(name = "Controlador para vuelo", description = "Operaciones CRUD para vuelo")
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

    @Operation(summary = "Este endpoint busca todos los vuelos despues de la fecha dada")
    @GetMapping("/find")
    public List<VueloResponse> findByFecha(@RequestParam Date fechaSalida) {
        return vueloService.findAllByFechaSalida(fechaSalida);
    }
    // actualizar el avion de un determinado vuelo

    @PutMapping("/update/pilotos/add")
    public ResponseBase<VueloResponse> updatePilotos(@RequestBody VueloRequestUpdatePilotos vueloRequest) {
        return vueloService.addPiltosToVuelo(vueloRequest);
    }

    @PutMapping("/update/pilotos/remove")
    public ResponseBase<VueloResponse> removePilotos(@RequestBody VueloRequestUpdatePilotos vueloRequest) {
        return vueloService.removePilotosFromVuelo(vueloRequest);
    }
}
