package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.example.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

@Service
public class SqsProducer {

    @Value("${aws.queueName}")
    private String queueName;

    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;

    public SqsProducer(AmazonSQS amazonSQSClient, ObjectMapper objectMapper) {
        this.amazonSQSClient = amazonSQSClient;
        this.objectMapper = objectMapper;
    }

    public void publishMessage(String id) {
        try {
            GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(queueName);

            Message message = new Message(id, "message", new Date());

            String messageJson = objectMapper.writeValueAsString(message);

            amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), messageJson);

            System.out.println("Message published: " + id);
        } catch (Exception e) {
            System.err.println("Queue Exception Message: " + e.getMessage());
        }
    }
}
