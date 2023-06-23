package com.promptpicture.backend.entrypoint.rest.exception;

import com.promptpicture.backend.core.exception.PromptException;
import com.promptpicture.backend.core.exception.business.BadRequestException;
import com.promptpicture.backend.entrypoint.rest.model.output.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestController
@ControllerAdvice(basePackages = "com.promptpicture.backend.entrypoint.rest.controller")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebApiErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(final BadRequestException e) {
        return getErrorDtoByException(e);
    }

    public ErrorResponse getErrorDtoByException(PromptException promptException) {


        return ErrorResponse
                .builder()
                .code(promptException.getErrorCode().getCode())
                .timestamp(OffsetDateTime.now())
                .message(promptException.getMessage())
                .build();
    }

}
