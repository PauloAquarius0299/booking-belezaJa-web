package com.paulotech.salao_api.payload.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String nome;
    private String email;
}
