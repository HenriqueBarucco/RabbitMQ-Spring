package com.henriquebarucco.producer.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    public RabbitMQConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    public static final String EXG_NAME_MARKETPLACE = "marketplace.direct";
    public static final String QUEUE_PRODUCT_LOG = "product.log";
    public static final String RK_PRODUCT_LOG = "product.log";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_PRODUCT_LOG, false, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXG_NAME_MARKETPLACE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(directExchange())
                .with(RK_PRODUCT_LOG);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
