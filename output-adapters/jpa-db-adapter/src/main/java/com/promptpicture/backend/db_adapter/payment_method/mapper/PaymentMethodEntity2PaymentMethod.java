package com.promptpicture.backend.db_adapter.payment_method.mapper;

import com.promptpicture.backend.core.payment_method.domain.PaymentMethod;
import com.promptpicture.backend.jpa.payment_method.entity.PaymentMethodEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMethodEntity2PaymentMethod {

    PaymentMethod map(PaymentMethodEntity from);

    List<PaymentMethod> toMapOfPaymentMethod(List<PaymentMethodEntity> from);
}
