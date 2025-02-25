package com.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.example.service.SqsProducer;

@SpringBootApplication
@EnableScheduling
public class SpringsqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsqsApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(SqsProducer sqsProducer) {
        return args -> {
            Thread.sleep(3000);
            for (int i = 0; i < 10; i++) {
                sqsProducer.publishMessage(String.valueOf(i));
            }
        };
    }
}
