package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.payment_method.PaymentMethodFacade;
import com.promptpicture.backend.entrypoint.rest.mapper.PaymentMethod2PaymentMethodResponseMapper;
import com.promptpicture.backend.entrypoint.rest.model.output.PaymentMethodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentMethodAdapter {

    private final PaymentMethodFacade paymentMethodFacade;
    private final PaymentMethod2PaymentMethodResponseMapper paymentMethod2PaymentMethodResponseMapper;

    public List<PaymentMethodResponse> getListOfPaymentMethod() {

        var listOfPaymentMethod = paymentMethodFacade.getListOfPaymentMethod();
        return paymentMethod2PaymentMethodResponseMapper.toListOfPaymentMethod(listOfPaymentMethod);
    }
}
