package com.promptpicture.backend.core.order.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Order {

    private Long id;
    private UUID externalCustomerId;
    private BigDecimal vatValue;
    private BigDecimal priceWithoutVat;
    private BigDecimal priceWithVat;
    private String name;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private String phone;
    private String email;
    private String description;
    private String paymentMethod;
    private List<PromptInOrder> promptInOrderList;

}
