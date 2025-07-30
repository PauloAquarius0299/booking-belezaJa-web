package com.paulotech.reserva_service.service.impl;

import com.paulotech.reserva_service.domain.BookingStatus;
import com.paulotech.reserva_service.dto.BookingRequest;
import com.paulotech.reserva_service.dto.SalonDto;
import com.paulotech.reserva_service.dto.ServiceDto;
import com.paulotech.reserva_service.dto.UserDto;
import com.paulotech.reserva_service.modal.Booking;
import com.paulotech.reserva_service.modal.SalaoReport;
import com.paulotech.reserva_service.repository.BookingRespository;
import com.paulotech.reserva_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRespository bookingRespository;


    @Override
    public Booking criarReserva(BookingRequest booking, UserDto usuarioDto, SalonDto salaoDto, Set<ServiceDto> servicoDtoSet) throws Exception {
        int totalDuracao = servicoDtoSet.stream().mapToInt(ServiceDto::getDuracao).sum();

        LocalDateTime reservaInicio = booking.getInicio();
        LocalDateTime reservaFim = reservaInicio.plusMinutes(totalDuracao);

        Boolean disponibilidade = verificarDisponibilidade(reservaInicio, reservaFim, salaoDto);

        int totalPreco=servicoDtoSet.stream().mapToInt(ServiceDto::getPreco).sum();

        Set<Long> idLista = servicoDtoSet.stream().map(ServiceDto::getId).collect(Collectors.toSet());

        Booking novaReserva = new Booking();
        novaReserva.setClienteId(usuarioDto.getId());
        novaReserva.setSalaoId(salaoDto.getId());
        novaReserva.setInicio(reservaInicio);
        novaReserva.setFim(reservaFim);
        novaReserva.setServicoId(idLista);
        novaReserva.setStatus(BookingStatus.PENDENTE);
        novaReserva.setTotalPrecos(totalPreco);

        return bookingRespository.save(novaReserva);
    }

    public Boolean verificarDisponibilidade(LocalDateTime inicio,
                                            LocalDateTime fim,
                                            SalonDto salaoDto) throws Exception {
        List<Booking> existeReservas= buscarReservasPorSalao(salaoDto.getId());
        LocalDateTime horaAbrirSalao = salaoDto.getAberto().atDate(inicio.toLocalDate());
        LocalDateTime horaFecharSalao = salaoDto.getFechado().atDate(fim.toLocalDate());

        if(inicio.isBefore(horaAbrirSalao) || fim.isAfter(horaFecharSalao)) {
            throw new Exception("Horário fora do horário de funcionamento do salão.");
        }

        for(Booking existeReserva : existeReservas) {
          LocalDateTime existeReservaInicio = existeReserva.getInicio();
          LocalDateTime existeReservaFim = existeReserva.getFim();

            if(horaAbrirSalao.isBefore(existeReservaFim) && existeReservaFim.isAfter(existeReservaInicio)){
                throw new Exception("horario não disponível, escolhe um horario diferente.");
            }


            if(horaAbrirSalao.isEqual(existeReservaInicio) || existeReservaInicio.isEqual(existeReservaFim)){
                throw new Exception("horario não disponível, escolhe um horario diferente.");
            }
        }

        return true;
    }

    @Override
    public List<Booking> buscarReservasPorCliente(Long clienteId) {
        return bookingRespository.findByClienteId(clienteId);
    }

    @Override
    public List<Booking> buscarReservasPorSalao(Long salaoId) {
        return bookingRespository.findBySalaoId(salaoId);
    }

    @Override
    public Booking buscarReservasPorId(Long id) throws Exception {
        Booking reserva = bookingRespository.findById(id).orElse(null);
        if (reserva == null) {
            throw new Exception("Reserva não encontrada com o ID: " + id);
        }
        return reserva;
    }

    @Override
    public Booking atualizarReserva(Long reservaId, BookingStatus status) throws Exception {
        Booking reserva = buscarReservasPorId(reservaId);

        reserva.setStatus(status);

        return bookingRespository.save(reserva);
    }

    @Override
    public List<Booking> buscarReservasPoData(LocalDate date, Long salaoId) {
        List<Booking> todosReservas = bookingRespository.findBySalaoId(salaoId);

        if(date==null){
            return todosReservas;
        }

        return todosReservas.stream()
                .filter(booking ->
                        mesmaData(booking.getInicio(), date.atStartOfDay()) ||
                                mesmaData(booking.getFim(), date.atStartOfDay())
                )
                .collect(Collectors.toList());
    }

    private boolean mesmaData(LocalDateTime inicio, LocalDateTime hora) {
        return inicio.toLocalDate().isEqual(hora.toLocalDate());
    }

    @Override
    public SalaoReport buscarSalaoReport(Long salaoId) {
        List<Booking> reservas = buscarReservasPorSalao(salaoId);

        int totalFaturamento = reservas.stream()
                .mapToInt(Booking::getTotalPrecos)
                .sum();

        Integer totalReservas = reservas.size();

        List<Booking> reservasCanceladas = reservas.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.CANCELADO)
                .collect(Collectors.toList());

        Double totalReembolso = reservasCanceladas.stream()
                .mapToDouble(Booking::getTotalPrecos)
                .sum();

        SalaoReport report = new SalaoReport();
        report.setSalaoId(salaoId);
        report.setReservasCanceladas(reservasCanceladas.size());
        report.setTotalReservas(totalReservas);
        report.setTotalFaturamento(totalFaturamento);
        report.setTotalReembolso(totalReembolso);

        return report;
    }
}
