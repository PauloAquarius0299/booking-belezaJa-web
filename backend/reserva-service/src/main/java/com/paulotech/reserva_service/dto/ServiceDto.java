package com.paulotech.reserva_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ServiceDto {
    private Long id;
    private String nome;
    private String descricao;
    private int preco;
    private int duracao;
    private Long salaoId;
    private Long categoria;
    private String image;
}
