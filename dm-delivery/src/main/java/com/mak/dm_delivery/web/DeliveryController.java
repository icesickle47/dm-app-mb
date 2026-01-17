package com.mak.dm_delivery.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class DeliveryController {

    private final RestClient dmApi;

    public DeliveryController(RestClient dmApiRestClient) {
        this.dmApi = dmApiRestClient;
    }

    @GetMapping("/probe-dm-api")
    public String probe() {
        return dmApi.get()
                .uri("/health")
                .retrieve()
                .body(String.class);
    }
}
