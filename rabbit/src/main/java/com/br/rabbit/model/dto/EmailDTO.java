package com.br.rabbit.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String message;
    private UserDTO user;
}
