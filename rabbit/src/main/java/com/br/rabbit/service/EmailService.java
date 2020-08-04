package com.br.rabbit.service;

import org.springframework.amqp.core.Message;

public interface EmailService {
    void sendEmail(Message message);
}
