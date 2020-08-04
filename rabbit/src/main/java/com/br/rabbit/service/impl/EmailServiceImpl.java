package com.br.rabbit.service.impl;

import com.br.rabbit.model.dto.EmailDTO;
import com.br.rabbit.service.EmailService;
import com.br.rabbit.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(Message message) {
        message.getMessageProperties().setContentType("application/json");
        byte[] body = message.getBody();

        EmailDTO emailDTO = JsonUtil.mapFromJson(new String(body), EmailDTO.class);

//        if ()
    }
}
