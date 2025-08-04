package com.paulotech.pagamento_service.payload.response;

import lombok.Data;

@Data
public class PaymentLinkResponse {
    private String url;
    private String id;
    private String payment;
}
