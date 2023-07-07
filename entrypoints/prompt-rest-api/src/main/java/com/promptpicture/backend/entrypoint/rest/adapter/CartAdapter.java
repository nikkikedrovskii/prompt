package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.cart.CartFacade;
import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.entrypoint.rest.mapper.CartItemRequest2CartItemMapper;
import com.promptpicture.backend.entrypoint.rest.model.input.CartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CartAdapter {

    private final CartFacade cartFacade;
    private final CartItemRequest2CartItemMapper cartItemRequest2CartItemMapper;

    public void addPromptToCart(CartItemRequest cartItemRequest) {
        var customerCartItem = cartItemRequest2CartItemMapper.map(cartItemRequest);
        cartFacade.addPromptToCart(customerCartItem);
    }

    public Cart getCartByExternalCustomerId(UUID externalCustomerId) {
       return cartFacade.getCartByExternalCustomerId(externalCustomerId);
    }

    public void deleteCartItem(Long cartItemId) {
        cartFacade.deleteCartItem(cartItemId);
    }
}
