package com.example.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${message}")
    private String message;

    @RequestMapping("message")
    public String message() {
        return this.message;
    }
}
