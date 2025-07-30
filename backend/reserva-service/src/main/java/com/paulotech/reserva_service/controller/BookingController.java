package com.paulotech.reserva_service.controller;

import com.paulotech.reserva_service.domain.BookingStatus;
import com.paulotech.reserva_service.dto.*;
import com.paulotech.reserva_service.mapper.BookingMapper;
import com.paulotech.reserva_service.modal.Booking;
import com.paulotech.reserva_service.modal.SalaoReport;
import com.paulotech.reserva_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> criarReservas(@RequestParam Long salaoId,
                                                 @RequestBody BookingRequest bookingRequest) throws Exception {
        UserDto usuario = new UserDto();
        usuario.setId(1L);

        SalonDto salao = new SalonDto();
        salao.setId(1L);
        salao.setAberto(LocalTime.of(8, 0));   // Abre 08:00
        salao.setFechado(LocalTime.of(20, 0)); // Fecha 20:00

        Set<ServiceDto> serviceDtoSet = new HashSet<>();

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setPreco(399);
        serviceDto.setDescricao(String.valueOf(45));
        serviceDto.setNome("Corte de cabelo");

        serviceDtoSet.add(serviceDto);

        Booking reserva = bookingService.criarReserva(bookingRequest, usuario, salao, serviceDtoSet);

        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/clientes")
    public ResponseEntity<Set<BookingDto>> buscarReservasPorCliente(){
        List<Booking> reservas = bookingService.buscarReservasPorCliente(1L);
        return ResponseEntity.ok(buscarReservasDtos(reservas));
    }

    @GetMapping("/salao")
    public ResponseEntity<Set<BookingDto>> buscarReservasPorSalao(){
        List<Booking> reservas = bookingService.buscarReservasPorSalao(1L);
        return ResponseEntity.ok(buscarReservasDtos(reservas));
    }

    private Set<BookingDto> buscarReservasDtos(List<Booking> reservas){
        return reservas.stream()
                .map(reserva -> {
                    return BookingMapper.toDto(reserva);
                }).collect(Collectors.toSet());
    }

    @GetMapping("/{reservaId}")
    public ResponseEntity<BookingDto> buscarReservasPorId(@PathVariable Long reservaId) throws Exception {
        Booking reservas = bookingService.buscarReservasPorId(reservaId);
        return ResponseEntity.ok(BookingMapper.toDto(reservas));
    }

    @PutMapping("/{reservaId}/status")
    public ResponseEntity<BookingDto> atualizarReservasStatus(@PathVariable Long reservaId,
                                                              @RequestParam BookingStatus status) throws Exception {
        Booking reservas = bookingService.atualizarReserva(reservaId, status);
        return ResponseEntity.ok(BookingMapper.toDto(reservas));
    }

    @GetMapping("/reservas/salao/{salaoId}/data/{data}")
    public ResponseEntity<List<BookingSlotDto>> buscarVagaReservaData(@PathVariable Long salaoId,
                                                                      @PathVariable String data) throws Exception {
        LocalDate localDate = LocalDate.parse(data);
        List<Booking> reservas = bookingService.buscarReservasPoData(localDate, salaoId);

        List<BookingSlotDto> slotsDtos = reservas.stream()
                .map(reserva -> {
                    BookingSlotDto slotDto = new BookingSlotDto();
                    slotDto.setInicio(reserva.getInicio());
                    slotDto.setFim(reserva.getFim());
                    return slotDto;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(slotsDtos);
    }

    @GetMapping("/reporte")
    public ResponseEntity<SalaoReport> buscarSalaoReporte(

    ) throws Exception {
        SalaoReport reporte = bookingService.buscarSalaoReport(1L);

        return ResponseEntity.ok(reporte);
    }
}
