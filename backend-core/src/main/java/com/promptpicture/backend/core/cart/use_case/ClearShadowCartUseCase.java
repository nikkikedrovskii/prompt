package com.promptpicture.backend.core.cart.use_case;

import com.promptpicture.backend.core.cart.adapter.CartRepositoryAdapter;
import com.promptpicture.backend.core.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClearShadowCartUseCase {

    private static final Integer CART_LIFE_TIME_DAYS = 2;
    private final CartRepositoryAdapter cartRepositoryAdapter;

    public void execute() {

        var listOfCart = cartRepositoryAdapter.getListOfCart();
        var listOfCartForDelete = getListOfCartForDelete(listOfCart);
        listOfCartForDelete.forEach(cartRepositoryAdapter::deleteShadowCart);
    }

    private List<Cart> getListOfCartForDelete(List<Cart> listOfCart) {
        return listOfCart.stream()
                .filter( cart -> ChronoUnit.DAYS.between(cart.getUpdatedAt().toLocalDate(), LocalDate.now()) > CART_LIFE_TIME_DAYS)
                .toList();
    }

}
