package com.promptpicture.backend.db_adapter.cart.adapter;

import com.promptpicture.backend.core.cart.adapter.CartRepositoryAdapter;
import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.db_adapter.cart.mapper.CartEntity2CartMapper;
import com.promptpicture.backend.jpa.cart.entity.CartEntity;
import com.promptpicture.backend.jpa.cart.entity.CartItemEntity;
import com.promptpicture.backend.jpa.cart.repository.CartEntityRepository;
import com.promptpicture.backend.jpa.cart.repository.CartItemEntityRepository;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import com.promptpicture.backend.jpa.prompt.repository.PromptEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartJpaRepositoryAdapter implements CartRepositoryAdapter {

    private final PromptEntityRepository promptEntityRepository;
    private final CartEntityRepository cartEntityRepository;
    private final CartItemEntityRepository cartItemEntityRepository;
    private final CartEntity2CartMapper cartEntity2CartMapper;


    @Override
    public void addPromptToCart(UUID externalCustomerId, Long promptId) {
        var promptEntity = promptEntityRepository.findById(promptId).get();
        var cartEntity = cartEntityRepository.findByExternalCustomerId(externalCustomerId);


        if (cartEntity.isPresent()){

            var cart = cartEntity.get();
            var cartItemEntity = cart.getCartItemEntities();
            var dbCartItemEntity = cartItemEntityRepository.findCartItemEntityByPromptId(promptId);

            if (dbCartItemEntity.isEmpty()) {
                var newItemCartEntity = createCartItemEntity(promptEntity, cart);
                cartItemEntityRepository.save(newItemCartEntity);
                cartItemEntity.add(newItemCartEntity);

                var totalPrice = cart.getTotalPrice();
                cart.setTotalPrice(totalPrice.add(newItemCartEntity.getPrice()));
                cart.setCartItemEntities(cartItemEntity);
                cartEntityRepository.save(cart);
            }
        } else {
            var newCartEntity = new CartEntity();
            var newItemCartEntity = createCartItemEntity(promptEntity, newCartEntity);
            newCartEntity.setTotalPrice(newItemCartEntity.getPrice());
            var cartItemEntity =  List.of(newItemCartEntity);
            newCartEntity.setCartItemEntities(cartItemEntity);
            newCartEntity.setExternalCustomerId(externalCustomerId);

            cartEntityRepository.save(newCartEntity);


        }

    }

    private CartItemEntity createCartItemEntity(PromptEntity prompt, CartEntity cartEntity) {
        var newItemCartEntity = new CartItemEntity();
        newItemCartEntity.setDescription(prompt.getDescription());
        newItemCartEntity.setPrice(prompt.getPrice());
        newItemCartEntity.setPromptEntity(prompt);
        newItemCartEntity.setCartEntity(cartEntity);
        return newItemCartEntity;
    }


    @Override
    public Cart getCartByExternalCustomerId(UUID externalCustomerId) {
        var cartEntity = cartEntityRepository.findByExternalCustomerId(externalCustomerId).get();
        return cartEntity2CartMapper.map(cartEntity);
    }
}
