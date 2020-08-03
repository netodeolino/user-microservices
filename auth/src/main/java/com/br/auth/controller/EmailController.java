package com.br.auth.controller;

import com.br.auth.model.dto.EmailRequest;
import io.swagger.annotations.ApiOperation;

public interface EmailController {
    @ApiOperation(value = "Send email")
    void sendEmail(EmailRequest email);
}
