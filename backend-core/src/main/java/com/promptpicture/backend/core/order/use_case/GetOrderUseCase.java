package com.promptpicture.backend.core.order.use_case;

import com.promptpicture.backend.core.order.adapter.OrderDetailsRepositoryAdapter;
import com.promptpicture.backend.core.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOrderUseCase {

    private final OrderDetailsRepositoryAdapter orderDetailsRepositoryAdapter;


    public Order execute(UUID customerExternalId) {
        var order = orderDetailsRepositoryAdapter.getOrderDetail(customerExternalId);
        calculateTotalPriceWithVat(order);
        return order;
    }

    private void calculateTotalPriceWithVat(Order order){
        var vatValue = order.getVatValue();
        var promptInOrderList = order.getPromptInOrderList();

        promptInOrderList.forEach(promptInOrder -> {
            var priceWithoutVat = promptInOrder.getPriceWithoutVat();
            var fullRate = new BigDecimal(1).subtract(vatValue.divide(new BigDecimal(100)));
            var priceWithVat = priceWithoutVat.divide(fullRate,2, RoundingMode.HALF_EVEN);
            promptInOrder.setPriceWithVat(priceWithVat);
        });

        order.setPromptInOrderList(promptInOrderList);
    }

}
