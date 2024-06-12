package org.minab.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig
{


    String directExchangeAuth="direct.exchange";
    String queueSaveSepet = "savesepet";
    String saveBindingKey = "key.savesepet";

    String queueFindHamburger = "findhamburger";
    String findHamburgerKey = "key.findhamburger";


    String queueFindUserProfile = "finduserprofileandupdatebalance";
    String keyFindUserProfile = "key.finduserprofileandupdatebalance";

    @Bean
    public DirectExchange directExchangeAuth(){
        return new DirectExchange(directExchangeAuth);
    }


    @Bean
    public Queue queueSaveSepet(){
        return new Queue(queueSaveSepet);
    }



    @Bean
    public Queue queueFindHamburger(){
        return new Queue(queueFindHamburger);
    }


    @Bean
    public Queue queueFindUserProfile(){
        return new Queue(queueFindUserProfile);
    }

    @Bean
    public Binding bindingSaveDirectExchange(Queue queueSaveSepet, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueSaveSepet).to(directExchangeAuth).with(saveBindingKey);
    }

    @Bean
    public Binding bindingFindHamburger(Queue queueFindHamburger, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueFindHamburger).to(directExchangeAuth).with(findHamburgerKey);
    }


    @Bean
    public Binding bindingFindUserProfile(Queue queueFindUserProfile, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueFindUserProfile).to(directExchangeAuth).with(keyFindUserProfile);
    }

    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
