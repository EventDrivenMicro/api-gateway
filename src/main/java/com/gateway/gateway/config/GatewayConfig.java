package com.gateway.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                 .route("books-microservice", r -> r
                        .path("/books/**")
                         .filters(f -> f.setPath("/books/{resource}"))
                         .uri("http://localhost:8083"))
                .route("search-gateway", r -> r
                        .path("/search/**")
                        .filters(f -> f.rewritePath("/search/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8085"))
                .build();
    }
}
