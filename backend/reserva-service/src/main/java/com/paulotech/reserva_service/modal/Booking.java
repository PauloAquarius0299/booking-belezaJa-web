package com.paulotech.reserva_service.modal;

import com.paulotech.reserva_service.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "reservas")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long salaoId;
    private Long clienteId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    @ElementCollection
    private Set<Long> servicoId;
    private BookingStatus status;
    private int totalPrecos;
}
