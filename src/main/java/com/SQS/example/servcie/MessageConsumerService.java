package com.SQS.example.servcie;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    @SqsListener("${cloud.aws.end-point.uri}")
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}
