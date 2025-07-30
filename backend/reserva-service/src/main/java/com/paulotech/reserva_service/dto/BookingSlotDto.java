package com.paulotech.reserva_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingSlotDto {
    private LocalDateTime inicio;
    private LocalDateTime fim;
}
