package com.spring.boot.async.bench.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange jobExchange() {
        return new DirectExchange("job.exchange");
    }

    @Bean
    public Queue jobQueue() {
        return new Queue("job.queue");
    }

    @Bean
    public Binding binding(Queue jobQueue, DirectExchange jobExchange) {
        return BindingBuilder.bind(jobQueue).to(jobExchange).with("job.routing.key");
    }
}
