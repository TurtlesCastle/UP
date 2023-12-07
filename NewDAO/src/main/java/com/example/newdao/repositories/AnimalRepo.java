package com.example.newdao.repositories;

import com.example.newdao.Animal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AnimalRepo extends JpaRepository<Animal, Long> {

    ArrayList<Animal> findAnimalBySpecies(String species);

}
