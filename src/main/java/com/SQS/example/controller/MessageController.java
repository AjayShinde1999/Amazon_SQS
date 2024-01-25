package com.SQS.example.controller;

import com.SQS.example.entity.User;
import com.SQS.example.servcie.MessageProducerService;
import com.SQS.example.servcie.UserProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private UserProducerService userProducerService;

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.CREATED)
    public String sendMessage(@RequestParam("message") String message) {
        messageProducerService.sendMessage(message);
        return "Sent Message";
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) {
        userProducerService.publishUser(user);
        return "User Published";
    }

}
