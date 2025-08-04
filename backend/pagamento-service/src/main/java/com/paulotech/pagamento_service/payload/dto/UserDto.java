package com.paulotech.pagamento_service.payload.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String nome;
    private String email;
}
