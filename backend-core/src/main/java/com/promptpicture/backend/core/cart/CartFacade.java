package com.promptpicture.backend.core.cart;

import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.core.cart.domain.CustomerCartItem;
import com.promptpicture.backend.core.cart.use_case.AddPromptToCartUseCase;
import com.promptpicture.backend.core.cart.use_case.DeleteCartItemUseCase;
import com.promptpicture.backend.core.cart.use_case.GetCartByExternalCustomerIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartFacade {

    private final AddPromptToCartUseCase addPromptToCartUseCase;
    private final GetCartByExternalCustomerIdUseCase getCartByExternalCustomerIdUseCase;
    private final DeleteCartItemUseCase deleteCartItemUseCase;

    public void addPromptToCart(CustomerCartItem customerCartItem) {
         addPromptToCartUseCase.execute(customerCartItem);
    }

    public Cart getCartByExternalCustomerId(UUID externalCustomerId) {
       return getCartByExternalCustomerIdUseCase.execute(externalCustomerId);
    }

    public void deleteCartItem(Long cartItemId) {
        deleteCartItemUseCase.execute(cartItemId);
    }


}
