package com.example.newdao.repositories;

import com.example.newdao.AppRole;
import com.example.newdao.Shroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RoleRepo extends JpaRepository<AppRole, Long> {
    AppRole findRoleByRoleName(String roleName);
}
