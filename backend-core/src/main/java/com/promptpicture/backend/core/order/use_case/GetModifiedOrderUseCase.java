package com.promptpicture.backend.core.order.use_case;

import com.promptpicture.backend.core.order.adapter.OrderDetailsRepositoryAdapter;
import com.promptpicture.backend.core.order.component.CheckModifiedOrderAndReturnChangedItemComponent;
import com.promptpicture.backend.core.order.domain.ModifiedOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetModifiedOrderUseCase {

    private final OrderDetailsRepositoryAdapter orderDetailsRepositoryAdapter;
    private final CheckModifiedOrderAndReturnChangedItemComponent checkModifiedOrderAndReturnChangedItemComponent;


    public ModifiedOrder execute(UUID customerExternalId) {
     var order = orderDetailsRepositoryAdapter.getOrderDetail(customerExternalId);
     var listOfChanges = checkModifiedOrderAndReturnChangedItemComponent.checkModifiedOrderAndReturnChangedItem(order);
     if (!listOfChanges.isEmpty()) {
        orderDetailsRepositoryAdapter.updateOrder(order);
     }
     return ModifiedOrder.builder().mapOfModifiedOrder(listOfChanges).build();
    }
}
