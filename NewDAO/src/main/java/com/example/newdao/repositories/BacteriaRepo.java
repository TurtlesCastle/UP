package com.example.newdao.repositories;

import com.example.newdao.Bacteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BacteriaRepo extends JpaRepository<Bacteria, Long> {
    ArrayList<Bacteria> findBacteriaBySpecies(String species);
}
