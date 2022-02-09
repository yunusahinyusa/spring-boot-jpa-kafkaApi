package com.ysahin.spring.jpa.kafkaApi.service;

import org.springframework.stereotype.Service;

@Service
public interface KafkaService {
    public void sendKafkaMessageProducer(String lastLine) throws Exception;
    public void listenMessageConsumer();
}
