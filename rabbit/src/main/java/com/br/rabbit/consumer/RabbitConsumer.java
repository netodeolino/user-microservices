package com.br.rabbit.consumer;

import com.br.rabbit.service.EmailService;
import com.br.rabbit.util.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = Constants.QUEUE)
    public void consumer(Message message) {
        this.emailService.sendEmail(message);
    }
}
