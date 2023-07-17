package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.cart.domain.CustomerCartItem;
import com.promptpicture.backend.entrypoint.rest.model.input.CartItemRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemRequest2CartItemMapper {

    CustomerCartItem map(CartItemRequest cartItemRequest);
}
