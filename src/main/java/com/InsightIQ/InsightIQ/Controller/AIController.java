package com.InsightIQ.InsightIQ.Controller;

import com.InsightIQ.InsightIQ.service.AIQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {


    private final AIQueryService aiQueryService;

    public AIController(AIQueryService aiQueryService) {
        this.aiQueryService = aiQueryService;
    }

    @PostMapping("/ask")
    public ResponseEntity<?>  ask (@RequestBody String question){
        return ResponseEntity.ok(aiQueryService.process(question));
    }

}
