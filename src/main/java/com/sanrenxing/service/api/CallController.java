package com.sanrenxing.service.api;

import com.sanrenxing.service.service.CallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/call")
public class CallController {

    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/start")
    public String startCall(@RequestParam UUID from, @RequestParam UUID to) {
        try {
            callService.startCall(from, to);
            return "Call started";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error starting call";
        }
    }

    @GetMapping("/stop")
    public String stopCall(@RequestParam UUID from, @RequestParam UUID to) {
        try {
            callService.stopCall(from, to);
            return "Call stopped";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error stopping call";
        }
    }
}



