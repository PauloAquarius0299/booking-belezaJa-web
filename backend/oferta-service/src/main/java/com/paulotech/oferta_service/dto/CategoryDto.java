package com.paulotech.oferta_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class CategoryDto {
    private  Long id;
    private String nome;
    private String image;
}
