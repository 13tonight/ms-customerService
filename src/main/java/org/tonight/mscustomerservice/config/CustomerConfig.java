package org.tonight.mscustomerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CustomerConfig {

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder().build();
    }

}
