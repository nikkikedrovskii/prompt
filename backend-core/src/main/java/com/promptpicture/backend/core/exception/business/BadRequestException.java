package com.promptpicture.backend.core.exception.business;

import com.promptpicture.backend.core.exception.PromptException;
import com.promptpicture.backend.core.exception.error_code.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends PromptException {

    public BadRequestException(ErrorCode errorCode) {

        super(errorCode);
    }

    public BadRequestException(ErrorCode errorCode, String... messageParams) {

        super(errorCode, messageParams);
    }

}
