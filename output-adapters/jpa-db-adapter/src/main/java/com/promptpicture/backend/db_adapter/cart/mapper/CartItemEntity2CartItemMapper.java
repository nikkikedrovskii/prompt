package com.promptpicture.backend.db_adapter.cart.mapper;

import com.promptpicture.backend.core.cart.domain.CartItem;
import com.promptpicture.backend.jpa.cart.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemEntity2CartItemMapper {

    @Mapping(target = "promptId", source = "promptEntity.id")
    @Mapping(target = "b64Json", source = "promptEntity.promptPictureEntity.fileB64JsonText")
    @Mapping(target = "resolution", source = "promptEntity.resolution")
    CartItem map(CartItemEntity cartItemEntity);

    List<CartItem> toListOfCartItem(List<CartItemEntity> cartItemEntity);
}
