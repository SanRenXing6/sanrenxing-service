package com.sanrenxing.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/call")
public class CallController {

    @GetMapping("/start")
    public String startCall() {
        return "Call started";
    }

    @GetMapping("/stop")
    public String stopCall() {
        return "Call stopped";
    }
}


