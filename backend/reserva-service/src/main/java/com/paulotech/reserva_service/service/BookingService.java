package com.paulotech.reserva_service.service;

import com.paulotech.reserva_service.domain.BookingStatus;
import com.paulotech.reserva_service.dto.BookingRequest;
import com.paulotech.reserva_service.dto.SalonDto;
import com.paulotech.reserva_service.dto.ServiceDto;
import com.paulotech.reserva_service.dto.UserDto;
import com.paulotech.reserva_service.modal.Booking;
import com.paulotech.reserva_service.modal.SalaoReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking criarReserva(BookingRequest booking,
                         UserDto usuarioDto,
                         SalonDto salaoDto,
                         Set<ServiceDto> servicoDtoSet) throws Exception;
    List<Booking> buscarReservasPorCliente(Long clienteId);
    List<Booking> buscarReservasPorSalao(Long salaoId);
    Booking buscarReservasPorId(Long reservaId) throws Exception;
    Booking atualizarReserva(Long id, BookingStatus status) throws Exception;
    List<Booking> buscarReservasPoData(LocalDate date, Long salaoId);
    SalaoReport buscarSalaoReport(Long salaoId);
}
