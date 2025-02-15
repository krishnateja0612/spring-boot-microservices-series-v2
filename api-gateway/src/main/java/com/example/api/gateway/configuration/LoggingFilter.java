/* Licensed under Apache-2.0 2021-2022 */
package com.example.api.gateway.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getURI().getPath().contains("/actuator")) {
            log.debug("Path of the request received -> {}", exchange.getRequest().getPath());
        } else {
            log.info("Path of the request received -> {}", exchange.getRequest().getPath());
        }
        return chain.filter(exchange);
    }
}
