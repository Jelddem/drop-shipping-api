package com.drop.shiping.api.drop_shiping_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class EpaycoConfig {
    @Value("${epayco.url.payments}")
    private String baseUrl;

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        if (baseUrl == null || baseUrl.isEmpty())
            throw new IllegalStateException("epayco.url.payments no configurada");

        return builder.baseUrl(baseUrl).build();
    }
}
