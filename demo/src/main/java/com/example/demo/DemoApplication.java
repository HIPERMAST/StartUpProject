package com.example.demo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.example.demo.apis.QueueConsumer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*
	@Bean
	public AmazonSQS amazonSQS() {
		String awsAccessKeyId = "ASIAQDR3JHSPIFCJTGGJ";
		String awsSecretAccessKey = "5KTy/PT7cI8nJVhpbu3/jJApKAwmrmvYwhEg3BRL";
		String awsRegion = "us-east-1";

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
		return AmazonSQSClient.builder()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsRegion)
				.build();
	}

	@Bean
	public QueueConsumer queueConsumer(AmazonSQS amazonSQS) {
		String queueUrl = "https://sqs.us-east-2.amazonaws.com/007640530078/StarterProject-danielbernal";
		return new QueueConsumer(queueUrl, amazonSQS);
	}
	*/
}

