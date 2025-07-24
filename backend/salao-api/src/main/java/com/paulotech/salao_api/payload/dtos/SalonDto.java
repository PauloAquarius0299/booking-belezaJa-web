package com.paulotech.salao_api.payload.dtos;


import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
