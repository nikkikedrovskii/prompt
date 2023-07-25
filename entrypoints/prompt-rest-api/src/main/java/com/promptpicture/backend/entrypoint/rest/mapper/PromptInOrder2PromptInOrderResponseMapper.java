package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.order.domain.PromptInOrder;
import com.promptpicture.backend.entrypoint.rest.model.output.PromptInOrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromptInOrder2PromptInOrderResponseMapper {

    PromptInOrderResponse map(PromptInOrder from);
}
