package com.promptpicture.dellerestclient.common;

import com.promptpicture.dellerestclient.config.DellEHttpClientFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestWebClientBuilderProvider {

    private final DellEHttpClientFactory httpClientFactory;

    public WebClient.Builder provide(String baseUrl) {

        var httpClient = httpClientFactory.createDellEHttpClient();

        var uriBuilderFactory = baseUrl != null
                ? new DefaultUriBuilderFactory(baseUrl)
                : new DefaultUriBuilderFactory();

        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        return WebClient.builder()
                .uriBuilderFactory(uriBuilderFactory)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter((clientRequest, exchangeFunction) -> exchangeFunction.exchange(clientRequest));
    }


}
