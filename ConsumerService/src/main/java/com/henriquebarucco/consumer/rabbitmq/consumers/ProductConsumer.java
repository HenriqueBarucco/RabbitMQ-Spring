package com.henriquebarucco.consumer.rabbitmq.consumers;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ProductConsumer {

    @RabbitListener(queues = { "product.log" })
    public void consumer(String dto) {
        try {
            log.info("Consumer received a message: {}", dto);
        } catch (Exception e) {
            log.error("Error processing message", e);
        }
    }
}
