package com.br.auth.controller;

import com.br.auth.model.dto.JwtRequest;
import com.br.auth.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<?> authenticate(JwtRequest jwtRequest);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(String id);

    void delete(String id);

    ResponseEntity<?> save(User user);

    ResponseEntity<?> update(String id, User user);
}
