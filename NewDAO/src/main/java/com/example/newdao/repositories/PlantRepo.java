package com.example.newdao.repositories;

import com.example.newdao.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PlantRepo extends JpaRepository<Plant, Long> {
    ArrayList<Plant> findPlantBySpecies(String species);
}
