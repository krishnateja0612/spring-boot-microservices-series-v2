/*** Licensed under Apache-2.0 2021-2023 ***/
package com.example.catalogservice.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(proxyBeanMethods = false)
public class ObservabilityConfiguration {

    private final ApplicationProperties applicationProperties;

    public ObservabilityConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    // To have the @Observed support we need to register this aspect
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    WebClient webClient() {
        return WebClient.builder().baseUrl(applicationProperties.getInventoryServiceUrl()).build();
    }
}
