package com.promptpicture.backend.core.cart.use_case;

import com.promptpicture.backend.core.cart.adapter.CartRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddPromptToCartUseCase {

    private final CartRepositoryAdapter cartRepositoryAdapter;

    public void execute(UUID externalCustomerId, Long promptId) {
        cartRepositoryAdapter.addPromptToCart(externalCustomerId, promptId);
    }

}
