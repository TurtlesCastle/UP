package com.example.finals.repos;

import com.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}
