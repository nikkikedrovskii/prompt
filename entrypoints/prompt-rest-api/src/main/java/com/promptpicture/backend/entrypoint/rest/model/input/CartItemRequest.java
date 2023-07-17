package com.promptpicture.backend.entrypoint.rest.model.input;

import lombok.Data;

import java.util.UUID;

@Data
public class CartItemRequest {

    private UUID externalCustomerId;
    private Long promptId;
    private String resolution;

}
