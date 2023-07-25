package com.promptpicture.backend.db_adapter.order.mapper;

import com.promptpicture.backend.core.order.domain.Order;
import com.promptpicture.backend.jpa.order.entity.OrderDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PromptInOrderEntity2PromptInOrderMapper.class)
public interface OrderDetailEntity2OrderMapper {

    @Mapping(target = "paymentMethod", source = "paymentMethodEntity.paymentMethodName")
    @Mapping(target = "promptInOrderList", source = "promptInOrderEntityList")
    Order map(OrderDetailsEntity from);
}
