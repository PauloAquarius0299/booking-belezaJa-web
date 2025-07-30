package com.paulotech.reserva_service.mapper;

import com.paulotech.reserva_service.dto.BookingDto;
import com.paulotech.reserva_service.modal.Booking;

public class BookingMapper {
    public static BookingDto toDto(Booking booking){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setSalaoId(booking.getSalaoId());
        bookingDto.setClienteId(booking.getClienteId());
        bookingDto.setInicio(booking.getInicio());
        bookingDto.setFim(booking.getFim());
        bookingDto.setServicoId(booking.getServicoId());
        bookingDto.setStatus(booking.getStatus());

        return bookingDto;
    }
}
