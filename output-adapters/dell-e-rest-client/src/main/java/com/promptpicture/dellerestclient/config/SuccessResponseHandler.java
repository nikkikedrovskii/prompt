package com.promptpicture.dellerestclient.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SuccessResponseHandler {

    private final ObjectMapper objectMapper;

    public <T, U> U successResponse(
            final ClientResponse clientResponse,
            final Class<T> originalResponseBodyType,
            final Function<T, U> responseBodyMapper) {

        return successResponse(clientResponse, originalResponseBodyType, responseBodyMapper, objectMapper);
    }

    public <T, U> U successResponse(
            final ClientResponse clientResponse,
            final Class<T> originalResponseBodyType,
            final Function<T, U> responseBodyMapper,
            final ObjectMapper responseObjectMapper) {

        if (originalResponseBodyType == Void.class) {
            return null;
        }

        final ByteArrayResource block = clientResponse.bodyToMono(ByteArrayResource.class).block();

        final byte[] bytes = block.getByteArray();

        try {
            return responseBodyMapper.apply(responseObjectMapper.readValue(bytes, originalResponseBodyType));
        }
        catch (final IOException e) {

            return null;
        }
    }
}
