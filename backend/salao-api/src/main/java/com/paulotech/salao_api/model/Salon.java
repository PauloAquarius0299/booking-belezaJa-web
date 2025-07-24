package com.paulotech.salao_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "salons")
@Data
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ElementCollection
    private List<String> images;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private Long idUsuarioDono;

    @Column(nullable = false)
    private LocalTime aberto;

    @Column(nullable = false)
    private LocalTime fechado;
}
