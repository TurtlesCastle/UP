package com.example.newdao.repositories;

import com.example.newdao.Shroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ShroomRepo  extends JpaRepository<Shroom, Long> {
    ArrayList<Shroom> findShroomBySpecies(String species);
}
