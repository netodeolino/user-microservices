package com.br.rabbit.model.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String login;
    private String password;
    private Date createdDate;
    private Date updatedDate;
    private String email;
    private Boolean admin;
}
