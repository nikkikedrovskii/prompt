package com.promptpicture.backend.core.exception;

import com.promptpicture.backend.core.exception.error_code.ErrorCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class PromptException extends RuntimeException {

    private final ErrorCode errorCode;
    private final List<String> messageParams;
    private final List<String> internalMessages = new ArrayList<>();


    public PromptException(ErrorCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.messageParams = Collections.emptyList();
    }

    public PromptException(ErrorCode errorCode, String... messageParams) {

        super(errorCode.getMessage() + Arrays.toString(messageParams));
        this.errorCode = errorCode;
        this.messageParams = Arrays.stream(messageParams).toList();
    }
}
