package com.polarbookshop.catalogservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //Adding this annotation in order to this Been reload after /actuator/refresh command is triggered.
              //This annotation is like a EventListener for Refresh event
public class HomeController {

    @Value("${polar.greeting}")
    private String message;

    @GetMapping("/")
    public String getGreeting(){
        return message;
    }
}
