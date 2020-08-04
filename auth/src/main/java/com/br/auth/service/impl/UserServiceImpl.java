package com.br.auth.service.impl;

import com.br.auth.exception.BadRequestException;
import com.br.auth.exception.NotFoundException;
import com.br.auth.model.dto.JwtRequest;
import com.br.auth.model.dto.JwtResponse;
import com.br.auth.model.entity.User;
import com.br.auth.repository.UserRepository;
import com.br.auth.service.UserService;
import com.br.auth.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = this.userRepository
                .findByLogin(login)
                .orElseThrow(() -> new BadRequestException("User not found with login: " + login));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAdmin().toString()));

        return new org.springframework.security.core.userdetails.User(
                login,
                "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                authorities);
    }

    @Override
    public JwtResponse authenticate(JwtRequest jwtRequest) {
        try {
            String login = jwtRequest.getLogin();
            String password = jwtRequest.getPassword();

            final UserDetails userDetails = this.loadUserByUsername(login);
            final String token = this.jwtTokenUtil.generateToken(userDetails);

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

            return new JwtResponse(token);
        } catch (Exception e) {
            log.error("ERROR - authenticate(): ", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public void delete(String id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User save(User user) {
        user.setAdmin(false);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        return this.userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        User userSaved = this.findById(id);

        userSaved.setName(user.getName());
        userSaved.setLogin(user.getLogin());
        userSaved.setEmail(user.getEmail());
        userSaved.setUpdatedDate(new Date());

        if (isUserLoggedAdmin()) {
            userSaved.setPassword(user.getPassword());
        }

        return this.userRepository.save(userSaved);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    private Boolean isUserLoggedAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().contains("true");
    }
}
