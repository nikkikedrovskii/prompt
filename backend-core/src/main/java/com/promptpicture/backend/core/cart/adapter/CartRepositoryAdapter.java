package com.promptpicture.backend.core.cart.adapter;

import com.promptpicture.backend.core.cart.domain.Cart;

import java.util.List;
import java.util.UUID;

public interface CartRepositoryAdapter {

    void addPromptToCart(UUID externalCustomerId, Long promptId);

    Cart getCartByExternalCustomerId(UUID externalCustomerId);

    List<Cart> getListOfCart();

    void deleteShadowCart(Cart cart);

}
