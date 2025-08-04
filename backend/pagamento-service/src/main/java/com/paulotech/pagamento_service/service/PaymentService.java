package com.paulotech.pagamento_service.service;

import com.paulotech.pagamento_service.domain.PaymentMethod;
import com.paulotech.pagamento_service.modal.PaymentOrder;
import com.paulotech.pagamento_service.payload.dto.BookingDto;
import com.paulotech.pagamento_service.payload.dto.UserDto;
import com.paulotech.pagamento_service.payload.response.PaymentLinkResponse;
import com.stripe.model.PaymentLink;


public interface PaymentService {
    PaymentLinkResponse criarOrdem(
            UserDto userDto,
            BookingDto bookingDto,
            PaymentMethod paymentMethod
    );

    PaymentOrder buscarOrdemPorId(Long id) throws Exception;
    PaymentOrder buscarPagamentOrderPorPagamentoId(Long pagamentoId);

    PaymentOrder buscarPagamentOrderPorPagamentoId(String pagamentoId);

//    PaymentLink criarMercadoPagoLink(UserDto userDto, Long quantia, Long ordemId);
    String criarStripeLink(
            UserDto userDto,
            Long quantia,
            Long ordemId);

}
