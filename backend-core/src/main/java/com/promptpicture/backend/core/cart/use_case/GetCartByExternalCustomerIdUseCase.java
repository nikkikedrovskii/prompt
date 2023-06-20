package com.promptpicture.backend.core.cart.use_case;

import com.promptpicture.backend.core.cart.adapter.CartRepositoryAdapter;
import com.promptpicture.backend.core.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCartByExternalCustomerIdUseCase {

    private final CartRepositoryAdapter cartRepositoryAdapter;

    public Cart execute(UUID externalCustomerId) {
        return cartRepositoryAdapter.getCartByExternalCustomerId(externalCustomerId);
    }
}
