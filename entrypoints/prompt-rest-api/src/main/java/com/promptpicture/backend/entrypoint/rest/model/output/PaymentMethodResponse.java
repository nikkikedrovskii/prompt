package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Data;

@Data
public class PaymentMethodResponse {

    private Long id;
    private String paymentMethodName;
    private String description;
}
