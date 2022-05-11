package com.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationFilterConfig> {

    @Override
    public GatewayFilter apply(AuthenticationFilterConfig config) {
        return (exchange, chain) -> {
            log.info("request filter config: {}", config.getText());

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // internal user me
            List<String> authorizations = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION);
            if (authorizations.isEmpty()) {
                return unAuthorization(response);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> unAuthorization(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = response.bufferFactory().wrap("empty authorization".getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

}
