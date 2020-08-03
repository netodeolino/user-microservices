package com.br.auth.controller;

import com.br.auth.model.dto.JwtRequest;
import com.br.auth.model.dto.JwtResponse;
import com.br.auth.model.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

public interface UserController {
    @ApiOperation(
            value = "Authenticate the user",
            response = JwtResponse.class)
    ResponseEntity<?> authenticate(JwtRequest jwtRequest);

    @ApiOperation(
            value = "Find all users",
            response = User.class,
            responseContainer = "List")
    ResponseEntity<?> findAll();

    @ApiOperation(
            value = "Find user by id",
            response = User.class)
    ResponseEntity<?> findById(String id);

    @ApiOperation(
            value = "Delete user by id")
    void delete(String id);

    @ApiOperation(
            value = "Save user",
            response = User.class)
    ResponseEntity<?> save(User user);

    @ApiOperation(
            value = "Update user",
            response = User.class)
    ResponseEntity<?> update(String id, User user);
}
