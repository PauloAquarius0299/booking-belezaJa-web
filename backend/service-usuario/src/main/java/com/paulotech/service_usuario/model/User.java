package com.paulotech.service_usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String nomeUsuario;

    @NotBlank(message = "o email é obrigatorio")
    @Email(message="Email deve ser validado")
    private String email;

    private String telefone;

    @NotBlank(message = "cargo é obrigatorio")
    private String cargo;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotBlank(message = "senha é obrigatorio")
    private String password;

}
