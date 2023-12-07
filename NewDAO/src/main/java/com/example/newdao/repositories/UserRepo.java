package com.example.newdao.repositories;

import com.example.newdao.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserRepo  extends JpaRepository<AppUser, Long> {
    AppUser findUserByeMail(String eMail);
    AppUser findUserByLogin(String login);
}
