package com.InsightIQ.InsightIQ.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Health")
public class HealthCheckController {

    @GetMapping("/ok")
    public String HealthCheck() {
        return "The api Health is ok , Running ";
    }
    @GetMapping("/api/check")

    public String check() {
        return "The api Health is ok , Running ";
    }

}
