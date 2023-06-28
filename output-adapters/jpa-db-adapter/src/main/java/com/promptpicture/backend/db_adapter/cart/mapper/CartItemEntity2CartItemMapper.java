package com.promptpicture.backend.db_adapter.cart.mapper;

import com.promptpicture.backend.core.cart.domain.CartItem;
import com.promptpicture.backend.jpa.cart.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemEntity2CartItemMapper {

    @Mapping(target = "promptId", source = "promptEntity.id")
    @Mapping(target = "b64Json", source = "promptEntity.promptPictureEntity.fileB64JsonText")
    @Mapping(target = "resolution", source = "promptEntity.resolution")
    @Mapping(target = "priceWithVat", source = "from", qualifiedByName = "calculatePriceWithVat")
    CartItem map(CartItemEntity from);

    List<CartItem> toListOfCartItem(List<CartItemEntity> cartItemEntity);

    @Named("calculatePriceWithVat")
    default BigDecimal calculatePriceWithVat(CartItemEntity from){
        var totalPrice = from.getPrice();
        var vatRate = from.getCartEntity().getVatEntity().getVatRate();
        var fullRate = new BigDecimal(1).subtract(vatRate.divide(new BigDecimal(100)));
        return totalPrice.divide(fullRate,2, RoundingMode.HALF_EVEN);
    }
}
