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
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        log.info("aaaa");
        return builder.routes()
                .route("content route", r -> r
                        .path("/contents/**")
//                        .filters(f -> f
//                                .rewritePath("/order/(.*)", "/$1")
//                                .addRequestHeader("X-Order-Header", "order"))
//                        .metadata("orderKey", "orderValue")
                        .uri("http://localhost:7777"))
                .route("review route", r -> r
                        .path("/reviews/**")
//                        .filters(f -> f
//                                .prefixPath("/v1")
//                                .addResponseHeader("X-TestHeader", "rewrite_empty_response")
//                                .modifyResponseBody(String.class, String.class,
//                                        (exchange, s) -> {
//                                            if (s == null) {
//                                                return Mono.just("emptybody");
//                                            }
//                                            return Mono.just(s.toUpperCase());
//                                        }))
                        .uri("http://localhost:9999"))
//                .route("debug_route", r -> r
//                        .order(-1)
//                        .query("debug", "1")
//                        .filters(f -> f
//                                .filter(debugGatewayFilter))
//                        .uri("http://localhost:9999"))
                .build();
    }


}
