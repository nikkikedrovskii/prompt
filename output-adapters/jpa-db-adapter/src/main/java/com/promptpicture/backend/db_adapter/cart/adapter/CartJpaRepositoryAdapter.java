package com.promptpicture.backend.db_adapter.cart.adapter;

import com.promptpicture.backend.core.cart.adapter.CartRepositoryAdapter;
import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.core.exception.business.BadRequestException;
import com.promptpicture.backend.db_adapter.cart.mapper.CartEntity2CartMapper;
import com.promptpicture.backend.jpa.cart.entity.CartEntity;
import com.promptpicture.backend.jpa.cart.entity.CartItemEntity;
import com.promptpicture.backend.jpa.cart.repository.CartEntityRepository;
import com.promptpicture.backend.jpa.cart.repository.CartItemEntityRepository;
import com.promptpicture.backend.jpa.customer.repository.CustomerEntityRepository;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import com.promptpicture.backend.jpa.prompt.repository.PromptEntityRepository;
import com.promptpicture.backend.jpa.vat.entity.VatEntity;
import com.promptpicture.backend.jpa.vat.repository.VatEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.promptpicture.backend.core.exception.error_code.ErrorCode.VAT_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class CartJpaRepositoryAdapter implements CartRepositoryAdapter {

    private static final String CZECH_REPUBLIC_COUNTRY_CODE = "CZ";
    private final PromptEntityRepository promptEntityRepository;
    private final CartEntityRepository cartEntityRepository;
    private final CartItemEntityRepository cartItemEntityRepository;
    private final CartEntity2CartMapper cartEntity2CartMapper;
    private final CustomerEntityRepository customerEntityRepository;
    private final VatEntityRepository vatEntityRepository;


    @Override
    public void addPromptToCart(UUID externalCustomerId, Long promptId) {
        var promptEntity = promptEntityRepository.findById(promptId).get();
        var cartEntity = cartEntityRepository.findByExternalCustomerId(externalCustomerId);


        if (cartEntity.isPresent()){

            var cart = cartEntity.get();
            var cartItemEntity = cart.getCartItemEntities();
            var promptIdCartItem = cartItemEntity.stream().map(CartItemEntity::getPromptEntity).map(PromptEntity::getId).toList();

            if (!promptIdCartItem.contains(promptId)) {
                var newItemCartEntity = createCartItemEntity(promptEntity, cart);
                cartItemEntityRepository.save(newItemCartEntity);
                cartItemEntity.add(newItemCartEntity);

                var totalPrice = cart.getTotalPrice();
                cart.setTotalPrice(totalPrice.add(newItemCartEntity.getPrice()));
                cart.setCartItemEntities(cartItemEntity);

                var vatRate = getVatRateByCustomerCountry(externalCustomerId);
                cart.setVatEntity(vatRate);

                cartEntityRepository.save(cart);
            }
        } else {
            var newCartEntity = new CartEntity();
            var newItemCartEntity = createCartItemEntity(promptEntity, newCartEntity);
            newCartEntity.setTotalPrice(newItemCartEntity.getPrice());
            var cartItemEntity =  List.of(newItemCartEntity);
            newCartEntity.setCartItemEntities(cartItemEntity);
            newCartEntity.setExternalCustomerId(externalCustomerId);

            var vatRate = getVatRateByCustomerCountry(externalCustomerId);
            newCartEntity.setVatEntity(vatRate);

            cartEntityRepository.save(newCartEntity);


        }

    }

    private VatEntity getVatRateByCustomerCountry(UUID externalCustomerId){
        var customerEntity = customerEntityRepository.findByExternalCustomerId(externalCustomerId);
        if (customerEntity.isPresent()){
            var customerCountry = customerEntity.get().getCountry();
            return vatEntityRepository.findVatEntityByCountryCode(customerCountry)
                    .orElseThrow(() -> new BadRequestException(VAT_NOT_EXIST));
        } else {
            return vatEntityRepository.findVatEntityByCountryCode(CZECH_REPUBLIC_COUNTRY_CODE)
                    .orElseThrow(() -> new BadRequestException(VAT_NOT_EXIST));
        }
    }

    private CartItemEntity createCartItemEntity(PromptEntity prompt, CartEntity cartEntity) {
        var newItemCartEntity = new CartItemEntity();
        newItemCartEntity.setDescription(prompt.getDescription());
        newItemCartEntity.setPrice(prompt.getPrice().getDefaultPrice());
        newItemCartEntity.setPromptEntity(prompt);
        newItemCartEntity.setCartEntity(cartEntity);
        return newItemCartEntity;
    }


    @Override
    public Cart getCartByExternalCustomerId(UUID externalCustomerId) {
        var cartEntity = cartEntityRepository.findByExternalCustomerId(externalCustomerId).get();
        return cartEntity2CartMapper.map(cartEntity);
    }

    @Override
    public List<Cart> getListOfCart() {
        var listOfCart = cartEntityRepository.findAll();
        return cartEntity2CartMapper.map(listOfCart);
    }

    @Override
    public void deleteShadowCart(Cart cart) {
        cartEntityRepository.deleteById(cart.getId());
    }
}
