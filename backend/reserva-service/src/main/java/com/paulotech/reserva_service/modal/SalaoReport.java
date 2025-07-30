package com.paulotech.reserva_service.modal;

import lombok.Data;

@Data
public class SalaoReport {
    private Long salaoId;
    private String salaoNome;
    private int totalFaturamento;
    private Integer totalReservas;
    private Integer reservasCanceladas;
    private Double totalReembolso;
}
