package com.paulotech.pagamento_service.service.impl;


import com.paulotech.pagamento_service.domain.PaymentMethod;
import com.paulotech.pagamento_service.modal.PaymentOrder;
import com.paulotech.pagamento_service.payload.dto.BookingDto;
import com.paulotech.pagamento_service.payload.dto.UserDto;
import com.paulotech.pagamento_service.payload.response.PaymentLinkResponse;
import com.paulotech.pagamento_service.repository.PaymentOrderRepository;
import com.paulotech.pagamento_service.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public PaymentLinkResponse criarOrdem(UserDto userDto, BookingDto bookingDto, PaymentMethod paymentMethod) {
        Long amount=(long) bookingDto.getTotalPreco();

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setQuantia(amount);
        paymentOrder.setPaymentMethod(paymentMethod);
        paymentOrder.setReservaId(bookingDto.getId());
        paymentOrder.setSalaoId(bookingDto.getSalaoId());
        PaymentOrder savePedido = paymentOrderRepository.save(paymentOrder);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

        if(paymentMethod.equals(PaymentMethod.DEBITO)){
            PaymentLink pagamento = criarMercadoPagoLink(userDto, savePedido.getQuantia(), savePedido.getId());

            String paymentUrl = pagamento.getUrl();
            String paymentUrlId = pagamento.getId();

            paymentLinkResponse.setUrl(paymentUrl);
            paymentLinkResponse.setPayment(paymentUrlId);

            savePedido.setPaymentLinkId(paymentUrlId);

            paymentOrderRepository.save(savePedido);

        } else {
            String paymentUrl = criarStripeLink(userDto, savePedido.getQuantia(), savePedido.getId());
            paymentLinkResponse.setPayment(paymentUrl);
        }

        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder buscarOrdemPorId(Long id) throws Exception {
        PaymentOrder paymentOrder = paymentOrderRepository.findById(id).orElse(null);
        if(paymentOrder==null){
            throw new Exception("pagamento do pedido não encontrado");
        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder buscarPagamentOrderPorPagamentoId(String pagamentoId) {
        return paymentOrderRepository.findByPaymentLinkId(pagamentoId);
    }

//    @Override
//    public PaymentLink criarMercadoPagoLink(UserDto userDto, Long Amount, Long ordemId) {
//
//        Long amount = Amount*100;
//
//        com.mercadopago.MercadoPagoConfig mercadoPago = new MercadoPagoConfig(stripeApiKey, stripeSecretKey);
//
//        JSONPObject paymentLinkRequest = new JSONPObject();
//        paymentLinkRequest.put("amount", amount);
//        paymentLinkRequest.put("description", "Pagamento de reserva no salão de beleza");
//
//        JSONPObject customer = new JSONPObject();
//        customer.put("name", userDto.getNome());
//        customer.put("email", userDto.getEmail());
//
//        paymentLinkRequest.put("customer", customer);
//
//        JSONPObject notify = new JSONPObject();
//        notify.put("email", true);
//
//        paymentLinkRequest.put("notify", notify);
//        paymentLinkRequest.put("reminder_enable", true);
//        paymentLinkRequest.put("callback_url", "http://localhost:8080/pagamento/callback");
//        paymentLinkRequest.put("callback_method", "GET");
//
//        return mercadoPago.paymentLink.create(paymentLinkRequest);
//    }

    @Override
    public String criarStripeLink(UserDto userDto, Long quantia, Long ordemId) {
        Stripe.apiKey=stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success/"+ordemId)
                .setCancelUrl("http://localhost:3000/payment/cancel/")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(quantia*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Pagamento de reserva no salão de beleza").build()
                                ).build()
                        ).build()
                );

        return "";
    }
}
