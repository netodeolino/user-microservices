package com.br.auth.controller.impl;

import com.br.auth.controller.EmailController;
import com.br.auth.model.dto.EmailRequest;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
@Api(tags = "EmailController")
public class EmailControllerImpl implements EmailController {
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED) 
    public void sendEmail(@RequestBody EmailRequest email) {

    }
}
