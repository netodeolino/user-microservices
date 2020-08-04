package com.br.auth.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
public class User {
    @Id
    @Getter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Indexed
    private String login;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Date createdDate;

    @Getter
    @Setter
    private Date updatedDate;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Boolean admin;
}
