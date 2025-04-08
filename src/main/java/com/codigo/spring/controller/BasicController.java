package com.codigo.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/basic")
public class BasicController {

    @GetMapping("/hola")
    public String holaMethod() {
        return "Hola";
    }

    @GetMapping("/adios")
    public String adiosMethod() {
        return "Adios";
    }
}
