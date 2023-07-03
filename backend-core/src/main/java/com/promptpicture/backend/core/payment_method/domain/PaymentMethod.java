package com.promptpicture.backend.core.payment_method.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMethod {

    private Long id;
    private String paymentMethodName;
    private String description;
}
