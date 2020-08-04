package com.br.auth.config;

import com.br.auth.util.Constants;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(Constants.EXCHANGE_NAME)
                .durable(true)
                .build();
    }
}
