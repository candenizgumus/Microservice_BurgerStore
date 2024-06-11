package com.minab.rabbitmq.consumer;

import com.minab.rabbitmq.model.ActivationMailModel;
import com.minab.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ActivationMailConsumer {
    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "activation.code.queue")
    public void sendActivationCodeMail(ActivationMailModel mailModel){
        mailSenderService.sendActivationMail(mailModel);
    }
}
