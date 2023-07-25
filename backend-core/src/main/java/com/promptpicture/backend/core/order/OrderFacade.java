package com.promptpicture.backend.core.order;

import com.promptpicture.backend.core.order.domain.ModifiedOrder;
import com.promptpicture.backend.core.order.domain.Order;
import com.promptpicture.backend.core.order.domain.OrderDetails;
import com.promptpicture.backend.core.order.use_case.GetModifiedOrderUseCase;
import com.promptpicture.backend.core.order.use_case.GetOrderUseCase;
import com.promptpicture.backend.core.order.use_case.SaveOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final SaveOrderUseCase saveOrderUseCase;
    private final GetModifiedOrderUseCase getModifiedOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    public void saveOrder(UUID externalCustomerId, OrderDetails orderDetails) {
        saveOrderUseCase.execute(externalCustomerId, orderDetails);
    }

    public ModifiedOrder getModifiedOrder(UUID externalCustomerId){
        return getModifiedOrderUseCase.execute(externalCustomerId);
    }

    public Order getCustomerOrder(UUID externalCustomerId){
        return getOrderUseCase.execute(externalCustomerId);
    }
}
