package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private BigDecimal priceWithoutVat;
    private BigDecimal priceWithVat;
    private String name;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String zipCode;
    private String phone;
    private String email;
    private String description;
    private String paymentMethod;
    private List<PromptInOrderResponse> promptInOrderList;
}
