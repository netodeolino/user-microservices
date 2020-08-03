package com.br.auth.service;

import com.br.auth.model.dto.JwtRequest;
import com.br.auth.model.dto.JwtResponse;
import com.br.auth.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    JwtResponse authenticate(JwtRequest jwtRequest);

    List<User> findAll();

    User findById(String id);

    void delete(String id);

    User save(User user);

    User update(String id, User user);
}
