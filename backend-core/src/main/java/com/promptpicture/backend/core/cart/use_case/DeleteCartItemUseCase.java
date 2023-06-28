package com.promptpicture.backend.core.cart.use_case;

import com.promptpicture.backend.core.cart.adapter.CartRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCartItemUseCase {

    private final CartRepositoryAdapter cartRepositoryAdapter;

    public void execute(Long cartItemId) {
        cartRepositoryAdapter.deleteCartItem(cartItemId);
    }

}
