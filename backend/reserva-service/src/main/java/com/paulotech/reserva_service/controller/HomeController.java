package com.paulotech.reserva_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String Funcionando(){
        return "Reserva Service is running!";
    }
}
