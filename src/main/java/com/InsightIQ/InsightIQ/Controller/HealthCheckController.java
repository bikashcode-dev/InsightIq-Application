package com.InsightIQ.InsightIQ.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Health")
public class HealthCheckController {

    @GetMapping
    public String HealthCheck() {
        return "Hello World Bikash";
    }

}
