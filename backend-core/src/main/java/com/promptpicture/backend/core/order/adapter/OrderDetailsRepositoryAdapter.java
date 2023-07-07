package com.promptpicture.backend.core.order.adapter;

import com.promptpicture.backend.core.order.domain.OrderDetails;

import java.util.UUID;

public interface OrderDetailsRepositoryAdapter {

    void saveOrder(UUID externalCustomerId, OrderDetails orderDetails);
}
