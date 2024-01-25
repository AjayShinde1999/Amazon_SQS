package com.SQS.example.servcie;

import com.SQS.example.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class UserProducerService {

    @Autowired
    private SqsClient sqsClient;

    @Value("${cloud.aws.end-point.uri}")
    private String queueUrl;

    public void publishUser(User user) {
        String user1 = convertUserToJson(user);
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(user1)
                .build();
    }

    private String convertUserToJson(User user) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
