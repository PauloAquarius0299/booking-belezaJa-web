package com.paulotech.category_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Data
public class SalonDto {

    private Long id;
    private String nome;
    private List<String> images;
    private String endereco;
    private String telefone;
    private String email;
    private String cidade;
    private Long idUsuarioDono;
    private LocalTime aberto;
    private LocalTime fechado;
}
