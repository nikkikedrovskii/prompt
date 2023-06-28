package com.promptpicture.backend.db_adapter.cart.mapper;

import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.jpa.cart.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring", uses = CartItemEntity2CartItemMapper.class)
public interface CartEntity2CartMapper {

    @Mapping(target = "cartItemOutputList", source = "cartItemEntities")
    @Mapping(target = "vatRate", source = "vatEntity.vatRate")
    @Mapping(target = "totalPriceWithVat", source = "from", qualifiedByName = "calculateTotalPriceWithVat")
    Cart map(CartEntity from);


    List<Cart> map(List<CartEntity> from);

   @Named("calculateTotalPriceWithVat")
   default BigDecimal calculateTotalPriceWithVat(CartEntity from){
        var totalPrice = from.getTotalPrice();
        var vatRate = from.getVatEntity().getVatRate();
        var fullRate = new BigDecimal(1).subtract(vatRate.divide(new BigDecimal(100)));
        return totalPrice.divide(fullRate,2,RoundingMode.HALF_EVEN);
    }

}
