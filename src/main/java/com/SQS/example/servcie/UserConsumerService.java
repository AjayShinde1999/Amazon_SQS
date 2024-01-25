package com.SQS.example.servcie;

import com.SQS.example.entity.User;
import com.SQS.example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserConsumerService {

    @Autowired
    private UserRepository userRepository;

    @SqsListener("${cloud.aws.end-point.uri}")
    public void receiveMessage(String message) {
        log.info("User Details : {}", message);
        // Assuming the message is a JSON string, convert it back to User object
        User user = convertJsonToUser(message);

        User savedUser = userRepository.save(user);
        log.info("save user : {}", savedUser);
    }

    private User convertJsonToUser(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
