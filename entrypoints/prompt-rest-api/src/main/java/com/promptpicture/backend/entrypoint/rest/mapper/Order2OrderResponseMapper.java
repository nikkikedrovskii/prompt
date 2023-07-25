package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.order.domain.Order;
import com.promptpicture.backend.entrypoint.rest.model.output.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PromptInOrder2PromptInOrderResponseMapper.class)
public interface Order2OrderResponseMapper {

    OrderResponse map(Order from);
}
