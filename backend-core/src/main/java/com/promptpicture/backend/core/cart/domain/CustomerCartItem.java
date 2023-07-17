package com.promptpicture.backend.core.cart.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerCartItem {

    private UUID externalCustomerId;
    private Long promptId;
    private String resolution;

}
