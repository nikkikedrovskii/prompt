package com.promptpicture.backend.core.order.use_case;

import com.promptpicture.backend.core.order.adapter.OrderDetailsRepositoryAdapter;
import com.promptpicture.backend.core.order.domain.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaveOrderUseCase {

    private final OrderDetailsRepositoryAdapter orderDetailsRepositoryAdapter;

    public void execute(UUID customerExternalId, OrderDetails orderDetails) {
        orderDetailsRepositoryAdapter.saveOrder(customerExternalId, orderDetails);
    }
}