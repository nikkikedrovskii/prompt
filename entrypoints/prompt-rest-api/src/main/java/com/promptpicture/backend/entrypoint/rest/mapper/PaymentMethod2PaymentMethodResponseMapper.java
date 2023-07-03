package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.payment_method.domain.PaymentMethod;
import com.promptpicture.backend.entrypoint.rest.model.output.PaymentMethodResponse;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PaymentMethod2PaymentMethodResponseMapper {

    PaymentMethodResponse map(PaymentMethod from);

    List<PaymentMethodResponse> toListOfPaymentMethod(List<PaymentMethod> from);
}
