package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.cart.CartFacade;
import com.promptpicture.backend.core.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CartAdapter {

    private final CartFacade cartFacade;

    public void addPromptToCart(UUID externalCustomerId, Long promptId) {
        cartFacade.addPromptToCart(externalCustomerId, promptId);
    }

    public Cart getCartByExternalCustomerId(UUID externalCustomerId) {
       return cartFacade.getCartByExternalCustomerId(externalCustomerId);
    }
}
