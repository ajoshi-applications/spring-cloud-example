package com.example.configuration;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@Configuration
public class ZipkinConfiguration {
    @Bean
    Sampler sampler() {
        return span -> true;
    }
}
