package com.promptpicture.backend.db_adapter.cart.adapter;

import com.promptpicture.backend.core.cart.adapter.CartRepositoryAdapter;
import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.core.cart.domain.CustomerCartItem;
import com.promptpicture.backend.core.exception.business.BadRequestException;
import com.promptpicture.backend.db_adapter.cart.mapper.CartEntity2CartMapper;
import com.promptpicture.backend.jpa.cart.entity.CartEntity;
import com.promptpicture.backend.jpa.cart.entity.CartItemEntity;
import com.promptpicture.backend.jpa.cart.repository.CartEntityRepository;
import com.promptpicture.backend.jpa.cart.repository.CartItemEntityRepository;
import com.promptpicture.backend.jpa.customer.repository.CustomerEntityRepository;
import com.promptpicture.backend.jpa.price.repository.PriceEntityRepository;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import com.promptpicture.backend.jpa.prompt.repository.PromptEntityRepository;
import com.promptpicture.backend.jpa.vat.entity.VatEntity;
import com.promptpicture.backend.jpa.vat.repository.VatEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final PriceEntityRepository priceEntityRepository;


    @Override
    public void addPromptToCart(CustomerCartItem customerCartItem) {
        var promptId = customerCartItem.getPromptId();
        var externalCustomerId = customerCartItem.getExternalCustomerId();
        var resolution = customerCartItem.getResolution();
        var promptEntity = promptEntityRepository.findById(promptId).get();
        var cartEntity = cartEntityRepository.findByExternalCustomerId(externalCustomerId);


       if (cartEntity.isPresent()){

            var cart = cartEntity.get();
            var cartItemEntity = cart.getCartItemEntities();
            var promptIdCartItem = cartItemEntity.stream().map(CartItemEntity::getPromptEntity).map(PromptEntity::getId).toList();

            if (!promptIdCartItem.contains(promptId)) {
                var newItemCartEntity = createCartItemEntity(promptEntity, cart, resolution);
                cartItemEntityRepository.save(newItemCartEntity);
                cartItemEntity.add(newItemCartEntity);

                var priceWithoutVat = cart.getPriceWithoutVat();
                cart.setPriceWithoutVat(priceWithoutVat.add(newItemCartEntity.getPriceEntity().getDefaultPrice()));
                cart.setCartItemEntities(cartItemEntity);

                var vatRate = getVatRateByCustomerCountry(externalCustomerId);
                cart.setVatValue(vatRate.getVatRate());
                cart.setPriceWithVat(calculateTotalPriceWithVat(cart.getPriceWithoutVat(),vatRate.getVatRate()));
                cartEntityRepository.save(cart);
            }
        } else {
            var newCartEntity = new CartEntity();
            var newItemCartEntity = createCartItemEntity(promptEntity, newCartEntity, resolution);
            var priceWithoutVat = newItemCartEntity.getPriceEntity().getDefaultPrice();
            newCartEntity.setPriceWithoutVat(priceWithoutVat);
            var cartItemEntity =  List.of(newItemCartEntity);
            newCartEntity.setCartItemEntities(cartItemEntity);
            newCartEntity.setExternalCustomerId(externalCustomerId);

            var vatRate = getVatRateByCustomerCountry(externalCustomerId);
            newCartEntity.setVatValue(vatRate.getVatRate());
            newCartEntity.setPriceWithVat(calculateTotalPriceWithVat(priceWithoutVat,vatRate.getVatRate()));
            cartEntityRepository.save(newCartEntity);


        }

    }

    private BigDecimal calculateTotalPriceWithVat(BigDecimal priceWithoutVat, BigDecimal vatRate){
        var fullRate = new BigDecimal(1).subtract(vatRate.divide(new BigDecimal(100)));
        return priceWithoutVat.divide(fullRate,2, RoundingMode.HALF_EVEN);
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

   private CartItemEntity createCartItemEntity(PromptEntity prompt, CartEntity cartEntity, String resolution) {
        var price = priceEntityRepository.findByResolution(resolution).get();
        var newItemCartEntity = new CartItemEntity();
        newItemCartEntity.setPriceEntity(price);
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

    @Override
    @Transactional
    public void deleteCartItem(Long cartItemId) {
       var cartItemEntity = cartItemEntityRepository.findById(cartItemId).get();
        var cartItem = cartItemEntity.getCartEntity();
        var currentTotalPrice = cartItem.getPriceWithoutVat();
        var currentTotalPriceWithVat = cartItem.getPriceWithVat();

        var currentCartItemEntityList = cartItem.getCartItemEntities();
        currentCartItemEntityList.remove(cartItemEntity);

        var previousPrice = cartItemEntity.getPromptEntity().isIndividual() ?
                cartItemEntity.getPriceEntity().getIndividualPrice() :
                cartItemEntity.getPriceEntity().getDefaultPrice();
        var previousePriceWithVat = calculateTotalPriceWithVat(previousPrice,cartItem.getVatValue());
        cartItem.setPriceWithoutVat(currentTotalPrice.subtract(previousPrice));
        cartItem.setPriceWithVat(currentTotalPriceWithVat.subtract(previousePriceWithVat));

        cartItem.setCartItemEntities(currentCartItemEntityList);
        cartEntityRepository.save(cartItem);
    }
}
