package com.codigo.spring.controller;

import com.codigo.spring.entity.BoletosEntity;
import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.repository.BoletoRepository;
import com.codigo.spring.repository.PasajeroRepository;
import com.codigo.spring.response.PasajeroInfoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Operation(summary = "Trae la informacion del pasajero, incluyendo su boleto y vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "XXXXX",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PasajeroInfoResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"code\": 404,\n" +
                                    "    \"message\": \"Datos encontrados correctamente.\",\n" +
                                    "    \"data\": {\n" +
                                    "        \"nombre\": \"Scottie\",\n" +
                                    "        \"apellido\": \"Tellett\",\n" +
                                    "        \"boletos\": null\n" +
                                    "    }\n" +
                                    "}")
                    ))
    })
    @GetMapping("/find/boleto/{id}")
    public ResponseBase<PasajeroInfoResponse> findBoleto(@PathVariable int id) {
        List<BoletosEntity> boletos = boletoRepository.findBoletosByPasajero(id);
        PasajeroEntity pasajero = pasajeroRepository.findById(id).orElse(null);
        if(pasajero == null) {
            return null;
        }
        PasajeroInfoResponse pasajeroInfoResponse = new PasajeroInfoResponse();
        pasajeroInfoResponse.setNombre(pasajero.getNombre());
        pasajeroInfoResponse.setApellido(pasajero.getApellido());
        pasajeroInfoResponse.setBoletos(new ArrayList<>());
        if(boletos.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.of(pasajeroInfoResponse));
        }

        for(BoletosEntity boleto: boletos) {
            pasajeroInfoResponse.getBoletos().add(new PasajeroInfoResponse.BoletoInfo(
                    boleto.getVuelo().getOrigen(),
                    boleto.getVuelo().getDestino(),
                    boleto.getAsiento()
            ));
        }
        return new ResponseBase<>(
                Constants.CODE_SUCCESS,
                Constants.MESSAGE_SUCCESS_FIND,
                Optional.of(pasajeroInfoResponse));
    }
}
