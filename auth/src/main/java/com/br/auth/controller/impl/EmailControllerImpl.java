package com.br.auth.controller.impl;

import com.br.auth.controller.EmailController;
import com.br.auth.model.dto.EmailDTO;
import com.br.auth.model.dto.EmailRequest;
import com.br.auth.model.entity.User;
import com.br.auth.service.UserService;
import com.br.auth.util.Constants;
import com.br.auth.util.JsonUtil;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("email")
@Api(tags = "EmailController")
public class EmailControllerImpl implements EmailController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        User user = this.userService.findByLogin(emailRequest.getLogin());

        EmailDTO emailDTO = new EmailDTO(emailRequest.getMessage(), user);
        String json = JsonUtil.mapToJson(emailDTO);

        this.rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, "", json.getBytes());
    }
}
