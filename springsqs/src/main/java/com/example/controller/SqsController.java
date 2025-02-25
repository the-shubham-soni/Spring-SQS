package com.example.controller;

import com.example.service.SqsProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    private final SqsProducer sqsProducer;

    public SqsController(SqsProducer sqsProducer) {
        this.sqsProducer = sqsProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        sqsProducer.publishMessage(message);
        return "Message sent!";
    }
}
