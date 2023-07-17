package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.order.OrderFacade;
import com.promptpicture.backend.entrypoint.rest.mapper.OrderRequest2OrderDetailsMapper;
import com.promptpicture.backend.entrypoint.rest.model.input.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderAdapter {

    private final OrderFacade orderFacade;
    private final OrderRequest2OrderDetailsMapper orderRequest2OrderDetailsMapper;

    public void saveOrder(UUID externalCustomerId, OrderRequest orderRequest) {
        var orderDetails = orderRequest2OrderDetailsMapper.map(externalCustomerId,orderRequest);
        orderFacade.saveOrder(externalCustomerId,orderDetails);
    }
}
