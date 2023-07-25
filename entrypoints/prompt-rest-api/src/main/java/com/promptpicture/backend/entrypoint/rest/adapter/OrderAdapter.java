package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.order.OrderFacade;
import com.promptpicture.backend.entrypoint.rest.mapper.Order2OrderResponseMapper;
import com.promptpicture.backend.entrypoint.rest.mapper.OrderRequest2OrderDetailsMapper;
import com.promptpicture.backend.entrypoint.rest.model.input.OrderRequest;
import com.promptpicture.backend.entrypoint.rest.model.output.ModifiedOrderResponse;
import com.promptpicture.backend.entrypoint.rest.model.output.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderAdapter {

    private final OrderFacade orderFacade;
    private final OrderRequest2OrderDetailsMapper orderRequest2OrderDetailsMapper;
    private final Order2OrderResponseMapper order2OrderResponseMapper;

    public void saveOrder(UUID externalCustomerId, OrderRequest orderRequest) {
        var orderDetails = orderRequest2OrderDetailsMapper.map(externalCustomerId,orderRequest);
        orderFacade.saveOrder(externalCustomerId,orderDetails);
    }

    public ModifiedOrderResponse getModifiedOrder(UUID externalCustomerId){
      var modifiedOrder=  orderFacade.getModifiedOrder(externalCustomerId);
      return ModifiedOrderResponse.builder().modifiedOrderItem(modifiedOrder.getMapOfModifiedOrder()).build();
    }

    public OrderResponse getOrder(UUID externalCustomerId){
        var order=  orderFacade.getCustomerOrder(externalCustomerId);
        return order2OrderResponseMapper.map(order);
    }
}
