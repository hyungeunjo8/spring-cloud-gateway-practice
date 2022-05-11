package com.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class NonBlockingGatewayFilterFactory extends AbstractGatewayFilterFactory<String> {
    @Override
    public GatewayFilter apply(String config) {
        return (exchange, chain) -> WebClient.create()
                .get()
                .uri("http://localhost:7777/contents/slow")
                .retrieve()
                .bodyToMono
                        (String.class).flatMap(s -> {
                    System.out.println(s);
                    return chain.filter(exchange);
                });
    }

    private void block() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
