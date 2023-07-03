package com.promptpicture.backend.core.payment_method.use_case;

import com.promptpicture.backend.core.payment_method.adapter.PaymentMethodRepositoryAdapter;
import com.promptpicture.backend.core.payment_method.domain.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListOfPaymentMethodUseCase {

    private final PaymentMethodRepositoryAdapter paymentMethodRepositoryAdapter;

    public List<PaymentMethod> execute() {
        return paymentMethodRepositoryAdapter.getListOfPaymentMethod();
    }
}
