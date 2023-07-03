package com.promptpicture.backend.db_adapter.payment_method.adapter;

import com.promptpicture.backend.core.payment_method.adapter.PaymentMethodRepositoryAdapter;
import com.promptpicture.backend.core.payment_method.domain.PaymentMethod;
import com.promptpicture.backend.db_adapter.payment_method.mapper.PaymentMethodEntity2PaymentMethod;
import com.promptpicture.backend.jpa.payment_method.repository.PaymentMethodEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentMethodJpaRepositoryAdapter implements PaymentMethodRepositoryAdapter {

    private final PaymentMethodEntityRepository paymentMethodEntityRepository;
    private final PaymentMethodEntity2PaymentMethod paymentMethodEntity2PaymentMethod;

    @Override
    public List<PaymentMethod> getListOfPaymentMethod() {
        var listOfPaymentMethodEntity = paymentMethodEntityRepository.findAll();
        return paymentMethodEntity2PaymentMethod.toMapOfPaymentMethod(listOfPaymentMethodEntity);
    }
}
