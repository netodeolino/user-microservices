package com.br.auth.controller.impl;

import com.br.auth.controller.UserController;
import com.br.auth.model.dto.JwtRequest;
import com.br.auth.model.dto.JwtResponse;
import com.br.auth.model.entity.User;
import com.br.auth.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(tags = "UserController")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = this.userService.authenticate(jwtRequest);
        return jwtResponse != null ? ResponseEntity.ok(jwtResponse) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        this.userService.delete(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.save(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody User user) {
        return ResponseEntity.ok(this.userService.update(id, user));
    }
}