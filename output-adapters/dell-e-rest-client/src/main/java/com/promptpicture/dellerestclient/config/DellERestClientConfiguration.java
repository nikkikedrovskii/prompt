package com.promptpicture.dellerestclient.config;


import com.promptpicture.dellerestclient.factory.DellEWebClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Configuration
public class DellERestClientConfiguration {

    private final DellEWebClientFactory webClientFactory;

    @Bean(DellEClientBeanMetadata.DELL_E_BEAN_WEBCLIENT)
    public WebClient createWebClient() {
        return webClientFactory.create("https://api.openai.com");
    }

}
