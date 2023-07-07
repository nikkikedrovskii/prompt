package com.promptpicture.backend.entrypoint.rest.model.input;

import lombok.Data;

@Data
public class OrderRequest {

    Long cartId;
    String name;
    String lastName;
    String address;
    String city;
    String country;
    String zipCode;
    String phone;
    String contactEmail;
    String description;
    String paymentMethod;

}
