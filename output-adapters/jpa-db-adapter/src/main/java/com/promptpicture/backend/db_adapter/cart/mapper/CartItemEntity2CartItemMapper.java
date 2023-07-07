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
    @Mapping(target = "resolution", source = "priceEntity.resolution")
    @Mapping(target = "price", source = "from", qualifiedByName = "calculatePriceWithoutVat")
    @Mapping(target = "priceWithVat", source = "from", qualifiedByName = "calculatePriceWithVat")
    CartItem map(CartItemEntity from);

    List<CartItem> toListOfCartItem(List<CartItemEntity> cartItemEntity);

    @Named("calculatePriceWithoutVat")
    default BigDecimal calculatePriceWithoutVat(CartItemEntity from){
        var promptEntity = from.getPromptEntity();
        return promptEntity.isIndividual() ? from.getPriceEntity().getIndividualPrice() : from.getPriceEntity().getDefaultPrice();
    }
    @Named("calculatePriceWithVat")
    default BigDecimal calculatePriceWithVat(CartItemEntity from){
        var promptEntity = from.getPromptEntity();
        var priceWithoutVat = promptEntity.isIndividual() ?
                from.getPriceEntity().getIndividualPrice() : from.getPriceEntity().getDefaultPrice();
        var vatRate = from.getCartEntity().getVatValue();
        var fullRate = new BigDecimal(1).subtract(vatRate.divide(new BigDecimal(100)));
        return priceWithoutVat.divide(fullRate,0, RoundingMode.HALF_EVEN);
    }
}
