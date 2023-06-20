package com.promptpicture.backend.core.cart;

import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.core.cart.use_case.AddPromptToCartUseCase;
import com.promptpicture.backend.core.cart.use_case.GetCartByExternalCustomerIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartFacade {

    private final AddPromptToCartUseCase addPromptToCartUseCase;
    private final GetCartByExternalCustomerIdUseCase getCartByExternalCustomerIdUseCase;

    public void addPromptToCart(UUID externalCustomerId, Long promptId) {
         addPromptToCartUseCase.execute(externalCustomerId, promptId);
    }

    public Cart getCartByExternalCustomerId(UUID externalCustomerId) {
       return getCartByExternalCustomerIdUseCase.execute(externalCustomerId);
    }


}
