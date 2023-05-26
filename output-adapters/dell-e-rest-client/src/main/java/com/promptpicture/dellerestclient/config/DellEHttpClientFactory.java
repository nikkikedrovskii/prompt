package com.promptpicture.dellerestclient.config;

import org.springframework.stereotype.Component;
import reactor.netty.http.client.HttpClient;

@Component
public class DellEHttpClientFactory {

    private static final int MAX_RESPONSE_HEADER_SIZE = 16_384;

    public HttpClient createDellEHttpClient() {

        return HttpClient
                .create()
                .httpResponseDecoder(
                        httpResponseDecoderSpec -> httpResponseDecoderSpec.maxHeaderSize(MAX_RESPONSE_HEADER_SIZE));
    }

}
