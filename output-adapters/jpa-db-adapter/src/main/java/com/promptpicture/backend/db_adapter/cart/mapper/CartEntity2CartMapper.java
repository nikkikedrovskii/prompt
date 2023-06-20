package com.promptpicture.backend.db_adapter.cart.mapper;

import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.jpa.cart.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemEntity2CartItemMapper.class)
public interface CartEntity2CartMapper {

    @Mapping(target = "cartItemOutputList", source = "cartItemEntities")
    Cart map(CartEntity from);



}
