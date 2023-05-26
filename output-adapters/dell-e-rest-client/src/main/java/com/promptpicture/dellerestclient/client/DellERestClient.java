package com.promptpicture.dellerestclient.client;

import com.promptpicture.backend.core.delle.domain.DellERestConfigurationProperties;
import com.promptpicture.dellerestclient.config.SuccessResponseHandler;
import com.promptpicture.dellerestclient.domain.DellERequest;
import com.promptpicture.dellerestclient.domain.DellEResponse;
import com.promptpicture.dellerestclient.factory.DellEWebClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DellERestClient {

    private final DellEWebClientFactory dellEWebClientFactory;
    private final SuccessResponseHandler successResponseHandler;
    private final DellERestConfigurationProperties dellERestConfigurationProperties;

    public DellEResponse getPicture(DellERequest dellERequest) {

        var baseUrl = dellERestConfigurationProperties.getBaseUrl();
        var accessToken = dellERestConfigurationProperties.getAccessToken();

        var webClient = dellEWebClientFactory.create(baseUrl);
        var callSpec = webClient.post()
                .uri("/v1/images/generations")
                .header("Authorization","Bearer " + accessToken)
                .body(BodyInserters.fromValue(dellERequest));

        var response = callSpec.exchange().block();

        return toResponse(response, DellEResponse.class, Function.identity());
    }

    private <T, U> U toResponse(
            ClientResponse clientResponse,
            Class<T> responseBodyType,
            Function<T, U> responseBodyMapper) {

        return successResponseHandler
                .successResponse(clientResponse, responseBodyType, responseBodyMapper);
    }
}
