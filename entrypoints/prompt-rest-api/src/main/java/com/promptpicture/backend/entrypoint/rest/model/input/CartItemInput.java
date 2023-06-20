package com.promptpicture.backend.entrypoint.rest.model.input;

import lombok.Data;

import java.util.UUID;

@Data
public class CartItemInput {

    private UUID externalCustomerId;
    private Long promptId;

}
