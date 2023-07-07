package com.promptpicture.backend.core.order;

import com.promptpicture.backend.core.order.domain.OrderDetails;
import com.promptpicture.backend.core.order.use_case.SaveOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final SaveOrderUseCase saveOrderUseCase;

    public void saveOrder(UUID externalCustomerId, OrderDetails orderDetails) {
        saveOrderUseCase.execute(externalCustomerId, orderDetails);
    }

}
