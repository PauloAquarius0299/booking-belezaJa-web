package com.paulotech.pagamento_service.modal;

import com.paulotech.pagamento_service.domain.PaymentMethod;
import com.paulotech.pagamento_service.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ordem_pagamento")
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long quantia;
    @Column(nullable = false)
    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    private String paymentLinkId;
    @Column(nullable = false)
    private Long usuarioId;
    @Column(nullable = false)
    private Long reservaId;
    @Column(nullable = false)
    private Long salaoId;
}
