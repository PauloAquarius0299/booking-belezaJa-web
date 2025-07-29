package com.paulotech.oferta_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "ofertas")
public class ServiceOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private int preco;
    @Column(nullable = false)
    private int duracao;
    @Column(nullable = false)
    private Long salaoId;
    @Column(nullable = false)
    private Long categoriaId;
    private String image;
}
