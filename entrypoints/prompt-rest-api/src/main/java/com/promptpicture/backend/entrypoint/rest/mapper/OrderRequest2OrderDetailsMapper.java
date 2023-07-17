package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.order.domain.OrderDetails;
import com.promptpicture.backend.entrypoint.rest.model.input.OrderRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderRequest2OrderDetailsMapper {

    OrderDetails map(UUID externalCustomerId, OrderRequest from);
}
