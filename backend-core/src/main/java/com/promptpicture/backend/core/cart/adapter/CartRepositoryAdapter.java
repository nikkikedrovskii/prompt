package com.promptpicture.backend.core.cart.adapter;

import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.core.cart.domain.CustomerCartItem;

import java.util.List;
import java.util.UUID;

public interface CartRepositoryAdapter {

    void addPromptToCart(CustomerCartItem customerCartItem);

    Cart getCartByExternalCustomerId(UUID externalCustomerId);

    List<Cart> getListOfCart();

    void deleteShadowCart(Cart cart);

    void deleteCartItem(Long cartItemId);

    void deleteCartByExternalCustomerId(UUID externalCustomerId);

}
