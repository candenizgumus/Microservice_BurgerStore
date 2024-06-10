package com.candenizgumus.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig
{


    String directExchangeAuth="direct.exchange";
    String queueSaveSepet = "savesepet";
    String saveBindingKey = "key.savesepet";


    @Bean
    public DirectExchange directExchangeAuth(){
        return new DirectExchange(directExchangeAuth);
    }


    @Bean
    public Queue queueSaveSepet(){
        return new Queue(queueSaveSepet);
    }

    @Bean
    public Binding bindingSaveDirectExchange(Queue queueSaveSepet, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueSaveSepet).to(directExchangeAuth).with(saveBindingKey);
    }

}
