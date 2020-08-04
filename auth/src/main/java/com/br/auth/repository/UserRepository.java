package com.br.auth.repository;

import com.br.auth.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
}
