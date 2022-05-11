package com.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlockingGatewayFilterFactory extends AbstractGatewayFilterFactory<String> {

    @Override
    public GatewayFilter apply(String config) {
        return (exchange, chain) -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return chain.filter(exchange);
        };
    }
}
