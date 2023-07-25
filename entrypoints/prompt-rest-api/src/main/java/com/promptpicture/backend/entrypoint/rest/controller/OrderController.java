package com.promptpicture.backend.entrypoint.rest.controller;

import com.promptpicture.backend.entrypoint.rest.adapter.OrderAdapter;
import com.promptpicture.backend.entrypoint.rest.model.input.OrderRequest;
import com.promptpicture.backend.entrypoint.rest.model.output.ModifiedOrderResponse;
import com.promptpicture.backend.entrypoint.rest.model.output.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderAdapter orderAdapter;

    @PostMapping(value = {"/order/{externalCustomerId}"})
    public ResponseEntity<Void> saveCustomerOrder(@PathVariable UUID externalCustomerId, @RequestBody OrderRequest orderRequest) {
        orderAdapter.saveOrder(externalCustomerId,orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = {"/order/{externalCustomerId}/modified"})
    public ResponseEntity<ModifiedOrderResponse> getModifiedOrder(@PathVariable UUID externalCustomerId) {
        var response = orderAdapter.getModifiedOrder(externalCustomerId);
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = {"/order/{externalCustomerId}"})
    public ResponseEntity<OrderResponse> getCustomerOrder(@PathVariable UUID externalCustomerId) {
        var response =  orderAdapter.getOrder(externalCustomerId);
        return ResponseEntity.ok(response);
    }



}
