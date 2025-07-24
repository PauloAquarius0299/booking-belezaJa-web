package com.paulotech.salao_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api")
    public String HomeControllerHandler() {
        return "Bem-vindo ao Salão API! Acesse /api para interagir com os recursos disponíveis.";
    }
}
