package com.drop.shiping.api.drop_shiping_api.payments.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
@RequestMapping("/app/payments")
@CrossOrigin(originPatterns = "*")
public class PaymentController {
    private final RestClient restClient;

    @Value("${epayco.url.payments}")
    private String url;

    @Value("${epayco.api.username}")
    private String username;

    @Value("${epayco.api.password}")
    private String password;

    public PaymentController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping
    public Map<String, String> testRestClient() {
        return restClient.post()
                .uri("/login")
                .headers(headers -> headers.setBasicAuth(username, password))
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, String>>() {});
    }
}
