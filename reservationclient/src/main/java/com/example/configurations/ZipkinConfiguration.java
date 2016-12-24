package com.example.configurations;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@Configuration
public class ZipkinConfiguration {
    @Bean
    Sampler sampler() {
        return new Sampler() {
            @Override
            public boolean isSampled(Span span) {
                return true;
            }
        };
    }
}
