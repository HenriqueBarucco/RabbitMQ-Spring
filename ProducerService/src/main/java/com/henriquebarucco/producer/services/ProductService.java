package com.henriquebarucco.producer.services;

import com.henriquebarucco.producer.dtos.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.henriquebarucco.producer.configs.RabbitMQConfig.EXG_NAME_MARKETPLACE;
import static com.henriquebarucco.producer.configs.RabbitMQConfig.RK_PRODUCT_LOG;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final RabbitTemplate rabbitTemplate;

    public void createProduct(ProductDTO dto) {
        log.info("Sending a message to exchange. {}", dto.toString());
        rabbitTemplate.convertAndSend(EXG_NAME_MARKETPLACE, RK_PRODUCT_LOG, dto);
    }
}
