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
    @Mapping(target = "totalPrice", source = "priceWithoutVat")
    @Mapping(target = "totalPriceWithVat", source = "priceWithVat")
    Cart map(CartEntity from);

    List<Cart> map(List<CartEntity> from);

}
