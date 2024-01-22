package com.eventapp.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ApiGatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/api/v1/user/**")
                        .uri("http://localhost:8081"))
                .route("authentication", r -> r.path("/api/v1/**")
                        .uri("http://localhost:8082"))
                .route("event",r -> r.path("/api/v1/events/**")
                        .uri("http://localhost:8083"))
                .route("wishlist",r -> r.path("/api/v1/wishlist/**")
                        .uri("http://localhost:8084"))
                .build();
    }
}
