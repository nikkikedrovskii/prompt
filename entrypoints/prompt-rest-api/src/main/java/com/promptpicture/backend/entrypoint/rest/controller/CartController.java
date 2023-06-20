package com.promptpicture.backend.entrypoint.rest.controller;

import com.promptpicture.backend.core.cart.domain.Cart;
import com.promptpicture.backend.entrypoint.rest.adapter.CartAdapter;
import com.promptpicture.backend.entrypoint.rest.model.input.CartItemInput;
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
public class CartController {

    private final CartAdapter cartAdapter;

    @PostMapping(value = {"/cart"})
    public ResponseEntity<Void> addCartItem(@RequestBody CartItemInput cartItemInput) {

        var externalCustomerId = cartItemInput.getExternalCustomerId();
        var promptId = cartItemInput.getPromptId();
        cartAdapter.addPromptToCart(externalCustomerId,promptId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = {"/cart/{customerId}"})
    public ResponseEntity<Cart> getCartByExternalMerchantId(@PathVariable UUID customerId) {
        var response = cartAdapter.getCartByExternalCustomerId(customerId);
        return ResponseEntity.ok(response);
    }

}