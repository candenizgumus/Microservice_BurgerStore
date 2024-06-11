package org.candenizgumus.rabbitmq.producer;

import org.candenizgumus.rabbitmq.model.ActivationMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivationMailProducer {
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSendToRabbit(ActivationMailModel model) {
        rabbitTemplate.convertAndSend("direct.exchange",
                "activation.code.key",
                model);
    }
}
