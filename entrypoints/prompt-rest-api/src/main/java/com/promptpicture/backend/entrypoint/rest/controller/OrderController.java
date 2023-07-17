package com.promptpicture.backend.entrypoint.rest.controller;

import com.promptpicture.backend.entrypoint.rest.adapter.OrderAdapter;
import com.promptpicture.backend.entrypoint.rest.model.input.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> getListOfPictureByUserId(@PathVariable UUID externalCustomerId, @RequestBody OrderRequest orderRequest) {
        orderAdapter.saveOrder(externalCustomerId,orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
