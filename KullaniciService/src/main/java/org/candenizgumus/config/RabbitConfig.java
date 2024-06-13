package org.candenizgumus.config;

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


    String queueFindUserProfile = "finduserprofileandupdatebalance";
    String keyFindUserProfile = "key.finduserprofileandupdatebalance";

    String queueFindAuthById = "findauthbyid";
    String keyFindAuthById = "key.findauthbyid";



    @Bean
    public DirectExchange directExchangeAuth(){
        return new DirectExchange(directExchangeAuth);
    }


    @Bean
    public Queue queueSaveSepet(){
        return new Queue(queueSaveSepet);
    }


    @Bean
    public Queue queueFindUserProfile(){
        return new Queue(queueFindUserProfile);
    }

    @Bean
    public Queue queueFindAuthById(){
        return new Queue(queueFindAuthById);
    }

    @Bean
    public Binding bindingSaveDirectExchange(Queue queueSaveSepet, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueSaveSepet).to(directExchangeAuth).with(saveBindingKey);
    }

    @Bean
    public Binding bindingFindUserProfile(Queue queueFindUserProfile, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueFindUserProfile).to(directExchangeAuth).with(keyFindUserProfile);
    }

    @Bean
    public Binding bindingFindAuthById(Queue queueFindAuthById, DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueFindAuthById).to(directExchangeAuth).with(keyFindAuthById);
    }

    String activationCodeQueueName="activation.code.queue";
    String activationCodeBindingKey="activation.code.key";

    String resetPasswordQueueName="reset.password.queue";
    String resetPasswordBindingKey="reset.password.email.key";

    @Bean
    Queue activationCodeQueue(){
        return new Queue(activationCodeQueueName);
    }
    @Bean
    Binding bindingActivationCode(Queue activationCodeQueue, DirectExchange exchangeDirect){
        return BindingBuilder.bind(activationCodeQueue).to(exchangeDirect).with(activationCodeBindingKey);
    }

    @Bean
    Queue resetPasswordQueue(){
        return new Queue(resetPasswordQueueName);
    }
    @Bean
    Binding bindingResetPassword(Queue resetPasswordQueue,DirectExchange exchangeDirect){
        return BindingBuilder.bind(resetPasswordQueue).to(exchangeDirect).with(resetPasswordBindingKey);
    }

    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
