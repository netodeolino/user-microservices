package com.br.rabbit.service.impl;

import com.br.rabbit.model.dto.EmailDTO;
import com.br.rabbit.service.EmailService;
import com.br.rabbit.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmail(Message message) {
        message.getMessageProperties().setContentType("application/json");
        byte[] body = message.getBody();

        EmailDTO emailDTO = JsonUtil.mapFromJson(new String(body), EmailDTO.class);

        // funcionalidade confusa...
        if (emailDTO.getUser().getAdmin()) {
            send(emailDTO);
        }
    }

    private void send(EmailDTO emailDTO) {
        log.info("Sending email to: ", emailDTO.getUser().getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@ia-fortaleza.com");
        message.setTo(emailDTO.getUser().getEmail());
        message.setSubject("Teste Desenvolvedor Java");
        message.setText(emailDTO.getMessage());

        try {
            this.emailSender.send(message);
        } catch (Exception e) {
            log.error("Email not send: ", e.getMessage());
        }
    }
}
