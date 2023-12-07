package com.example.newdao.repositories;

import com.example.newdao.Virus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface VirusRepo extends JpaRepository<Virus, Long> {
    ArrayList<Virus> findVirusBySpecies(String species);
}
