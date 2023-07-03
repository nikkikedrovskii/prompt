package com.promptpicture.backend.core.payment_method.adapter;

import com.promptpicture.backend.core.payment_method.domain.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepositoryAdapter {

    List<PaymentMethod> getListOfPaymentMethod();
}
