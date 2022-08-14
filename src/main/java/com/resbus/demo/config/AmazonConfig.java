package com.resbus.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean  // with this annotation Spring instantiate this method, then we can inject it
    public AmazonS3 s3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                System.getProperty("awsAccessKey"),
                System.getProperty("awsSecretKey")
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials( new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
