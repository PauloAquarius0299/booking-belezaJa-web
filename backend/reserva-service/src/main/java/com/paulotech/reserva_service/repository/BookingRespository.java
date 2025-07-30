package com.paulotech.reserva_service.repository;

import com.paulotech.reserva_service.modal.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRespository extends JpaRepository<Booking, Long> {
    List<Booking> findByClienteId(Long clienteId);
    List<Booking> findBySalaoId(Long salaoId);
}
