package com.SQS.example.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;


@Service
public class MessageProducerService {

    @Value("${cloud.aws.end-point.uri}")
    private String queueUrl;

    @Autowired
    private SqsClient sqsClient;

    public void sendMessage(String message){
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();

        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendMessageRequest);
        System.out.println("Message sent. MessageId: " + sendMessageResponse.messageId());
    }
}
