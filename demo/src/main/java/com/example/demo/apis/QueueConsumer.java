package com.example.demo.apis;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueConsumer {

    private final AmazonSQS sqs;
    private final String queueUrl;

    private final Logger logger = LoggerFactory.getLogger(QueueConsumer.class);

    public QueueConsumer(String queueUrl, AmazonSQS sqs) {
        this.queueUrl = queueUrl;
        this.sqs = sqs;
    }

    public void startListening() {
        while (true) {
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
                    .withWaitTimeSeconds(20) // Adjust as needed
                    .withMaxNumberOfMessages(1);

            ReceiveMessageResult receiveMessageResult = sqs.receiveMessage(receiveMessageRequest);

            for (Message message : receiveMessageResult.getMessages()) {
                processMessage(message);
            }
        }
    }

    private void processMessage(Message message) {
        try {
            String messageBody = message.getBody();
            logger.info("Received message: " + messageBody);
            // Parse the message content (e.g., JSON) to determine the operation and book details
            // Perform save/update/delete logic based on the operation and book name

            // After processing, delete the message from the queue
            String receiptHandle = message.getReceiptHandle();
            sqs.deleteMessage(new DeleteMessageRequest(queueUrl, receiptHandle));
        } catch (AmazonServiceException e) {
            logger.error("AmazonServiceException: " + e.getMessage());
            // Maneja los errores de Amazon SQS (pueden incluir errores de autenticación).
        } catch (Exception e) {
            logger.error("Error processing message: " + message.getBody());
            // Maneja otros errores de procesamiento aquí.
        }
    }
}
