package com.smartera.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author yilmazsahin
 * @since 2/20/2024
 */
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient getWebClient() {
        return WebClient.builder().build();
    }
}
