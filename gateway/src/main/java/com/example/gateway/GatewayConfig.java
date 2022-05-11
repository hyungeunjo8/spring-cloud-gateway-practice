package com.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, AuthenticationGatewayFilterFactory authenticationGatewayFilterFactory) {
        return builder.routes()
                .route("content route", r -> r
                        .path("/contents/**")
                        .filters(f -> f.filter(authenticationGatewayFilterFactory.apply(new AuthenticationFilterConfig("contents config"))))
                        .uri("http://localhost:7777")
                )
                .route("review route", r -> r
                        .path("/reviews/**")
                        .filters(f -> f.filter(authenticationGatewayFilterFactory.apply(new AuthenticationFilterConfig("review config"))))
                        .uri("http://localhost:9999")
                )
                .build();
    }


}
