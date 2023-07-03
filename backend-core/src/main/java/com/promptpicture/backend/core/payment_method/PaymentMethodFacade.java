package com.promptpicture.backend.core.payment_method;

import com.promptpicture.backend.core.payment_method.domain.PaymentMethod;
import com.promptpicture.backend.core.payment_method.use_case.GetListOfPaymentMethodUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentMethodFacade {

    private final GetListOfPaymentMethodUseCase getListOfPaymentMethodUseCase;

    public List<PaymentMethod> getListOfPaymentMethod() {
        return getListOfPaymentMethodUseCase.execute();
    }

}
