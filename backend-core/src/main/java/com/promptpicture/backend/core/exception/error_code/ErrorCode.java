package com.promptpicture.backend.core.exception.error_code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {


    PROMPT_NOT_FOUND("badRequest.prompt.not.found", 2000),
    USER_DOES_NOT_EXIST("badRequest.user.does.not.exist", 2001),
    VAT_NOT_EXIST("badRequest.cart.not.found", 2002),
    PRICE_NOT_FOUND("badRequest.price.by.selected.resolution.not.found", 2003);

    private final String message;
    private final Integer code;
}
