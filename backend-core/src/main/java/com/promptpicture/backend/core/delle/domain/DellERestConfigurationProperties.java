package com.promptpicture.backend.core.delle.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app.delle")
public class DellERestConfigurationProperties {

    String accessToken;
    String baseUrl;
}
