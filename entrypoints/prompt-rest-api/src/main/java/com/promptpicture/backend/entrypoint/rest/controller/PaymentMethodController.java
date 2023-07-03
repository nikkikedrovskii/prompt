package com.promptpicture.backend.entrypoint.rest.controller;

import com.promptpicture.backend.entrypoint.rest.adapter.PaymentMethodAdapter;
import com.promptpicture.backend.entrypoint.rest.model.output.PaymentMethodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodAdapter paymentMethodAdapter;

    @GetMapping(value = {"/payment-method"})
    public ResponseEntity<List<PaymentMethodResponse>> getListOfTag() {
        var response = paymentMethodAdapter.getListOfPaymentMethod();
        return ResponseEntity.ok(response);
    }
}
