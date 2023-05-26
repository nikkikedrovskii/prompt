package com.promptpicture.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.promptpicture.backend", "com.promptpicture.dellerestclient"})
@ConfigurationPropertiesScan({"com.promptpicture.backend", "com.promptpicture.dellerestclient"})
public class PromptApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromptApplication.class, args);
    }

}
