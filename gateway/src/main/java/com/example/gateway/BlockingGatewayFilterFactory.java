package com.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class BlockingGatewayFilterFactory extends AbstractGatewayFilterFactory<String> {
    @Override
    public GatewayFilter apply(String config) {
        return (exchange, chain) -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForEntity("http://localhost:7777/contents/slow", String.class);
            return chain.filter(exchange);
        };
    }
}
