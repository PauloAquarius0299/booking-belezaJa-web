package com.paulotech.reserva_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingRequest {
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Set<Long> servicoIds;
}
