package com.paulotech.reserva_service.dto;

import com.paulotech.reserva_service.domain.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Long salaoId;
    private Long clienteId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Set<Long> servicoId;
    private BookingStatus status = BookingStatus.PENDENTE ;

}
