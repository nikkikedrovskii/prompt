package com.promptpicture.backend.core.order.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderDetails {

    private Long cartId;
    private UUID externalCustomerId;
    private String name;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private String phone;
    private String contactEmail;
    private String description;
    private String paymentMethod;

}
