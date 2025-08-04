package com.paulotech.pagamento_service.payload.dto;

import com.paulotech.pagamento_service.domain.BookingStatus;
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
    private Long totalPreco;

}
