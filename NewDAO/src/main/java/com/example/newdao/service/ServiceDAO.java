package com.example.newdao.service;

import com.example.newdao.*;
import com.example.newdao.Object;
import com.example.newdao.repositories.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDAO {
    private final AnimalRepo animalRepo;
    private final BacteriaRepo bacteriaRepo;
    private final PlantRepo plantRepo;
    private final ShroomRepo shroomRepo;
    private final VirusRepo virusRepo;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public ServiceDAO(AnimalRepo animalRepo, BacteriaRepo bacteriaRepo, PlantRepo plantRepo,
                         ShroomRepo shroomRepo,VirusRepo virusRepo, UserRepo userRepo, RoleRepo roleRepo)
    {
        this.animalRepo = animalRepo;
        this.bacteriaRepo = bacteriaRepo;
        this.plantRepo = plantRepo;
        this.shroomRepo = shroomRepo;
        this.virusRepo = virusRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    public AppUser getUser(String login)
    {
        return userRepo.findUserByLogin(login);
    }
    public List<AppUser> getUserRows()
    {
        List<AppUser> result = userRepo.findAll();
        return result;
    }
    public ArrayList<Object> getRows(String toConvert, String species)
    {
        if (species.isEmpty()) {
            ArrayList<Object> result = new ArrayList<>();
            switch (toConvert) {
                case ("Животные"):
                    for (Animal a : animalRepo.findAll()) {
                        result.add(a.toObject());
                    }
                    ;
                    break;
                case "Бактерии":
                    for (Bacteria a : bacteriaRepo.findAll()) {
                        result.add(a.toObject());
                    }
                    ;
                    break;
                case "Растения":
                    for (Plant a : plantRepo.findAll()) {
                        result.add(a.toObject());
                    }
                    ;
                    break;
                case "Грибы":
                    for (Shroom a : shroomRepo.findAll()) {
                        result.add(a.toObject());
                    }
                    ;
                    break;
                case "Вирусы":
                    for (Virus a : virusRepo.findAll()) {
                        result.add(a.toObject());
                    }
                    ;
                    break;
            }
            return result;
        }
        else return findAllBySpecies(toConvert, species);
    }

    public String postObject(String table, Object object)
    {
        switch (table)
        {
            case "Животные":
                Animal animal = object.toAnimal();
                try {
                    animalRepo.saveAndFlush(animal);
                }
                catch(ConstraintViolationException d)
                {
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }
                break;
            case "Бактерии":
                Bacteria bacteria = object.toBacteria();
                try{
                    bacteriaRepo.saveAndFlush(bacteria);
                }
                catch(ConstraintViolationException d)
                {
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }
                break;
            case "Растения":
                Plant plant = object.toPlant();
                try {
                    plantRepo.saveAndFlush(plant);
                }
                catch(ConstraintViolationException d)
                {
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }
                break;
            case "Грибы":
                Shroom shroom = object.toShroom();
                try {
                    shroomRepo.saveAndFlush(shroom);
                }
                catch(ConstraintViolationException d)
                {
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }
                break;
            case "Вирусы":
                Virus virus = object.toVirus();
                try {
                    virusRepo.saveAndFlush(virus);
                }
                catch(ConstraintViolationException d)
                {
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }
                break;
        }
        return "";
    }

    public void deleteObject(String table, int id)
    {
        switch (table)
        {
            case "Животные": Animal animal = animalRepo.findAll().stream().filter(a -> (int)a.ID == id).toList().get(0);
                animalRepo.delete(animal); break;
            case "Бактерии": Bacteria bacteria = bacteriaRepo.findAll().stream().filter(a -> (int)a.ID == id).toList().get(0);
                bacteriaRepo.delete(bacteria); break;
            case "Растения": Plant plant = plantRepo.findAll().stream().filter(a -> (int)a.ID == id).toList().get(0);
                plantRepo.delete(plant); break;
            case "Грибы": Shroom shroom = shroomRepo.findAll().stream().filter(a -> (int)a.ID == id).toList().get(0);
                shroomRepo.delete(shroom); break;
            case "Вирусы": Virus virus = virusRepo.findAll().stream().filter(a -> (int)a.ID == id).toList().get(0);
                virusRepo.delete(virus); break;
        }
    }
    public void deleteUser(String login)
    {
        userRepo.delete(userRepo.findUserByLogin(login));
    }
    public String putUser(String roleName, String login)
    {
        AppUser user = userRepo.findUserByLogin(login);
        AppRole role = roleRepo.findRoleByRoleName(roleName);
        if (role!=null)
        {
            user.setRole(role);
            userRepo.saveAndFlush(user);
            return "";
        }
        else return "Не вышло";
    }
    public String putObject(String table, int ID, Object object)
    {
        object.ID = ID;
        switch (table)
        {
            case "Животные":
                Animal animal = object.toAnimal();
                animal.ID = ID;
                try {
                    animalRepo.saveAndFlush(animal);
                }
                catch (ConstraintViolationException d){
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }


                break;
            case "Бактерии":
                Bacteria bacteria = object.toBacteria();
                bacteria.ID = ID;
                try {
                    bacteriaRepo.saveAndFlush(bacteria);
                }
                catch (ConstraintViolationException d){
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }
                break;
            case "Растения":
                Plant plant = object.toPlant();
                plant.ID = ID;
                try {
                    plantRepo.saveAndFlush(plant);
                }
                catch (ConstraintViolationException d){
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }

                break;
            case "Грибы":
                Shroom shroom = object.toShroom();
                shroom.ID = ID;
                try {
                    shroomRepo.saveAndFlush(shroom);
                }
                catch (ConstraintViolationException d){
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }

                break;
            case "Вирусы":
                Virus virus = object.toVirus();
                virus.ID = ID;
                try {
                    virusRepo.saveAndFlush(virus);
                }
                catch (ConstraintViolationException d){
                    return d.getMessage().substring(d.getMessage().indexOf("'")+1, d.getMessage().indexOf("', propertyPath"));
                }

                break;
        }
        return "";
    }

    public ArrayList<Object> findAllBySpecies(String table, String species)
    {
        ArrayList<Object> result = new ArrayList<>();
        switch(table)
        {
            case "Животные":
                for (Animal animal: animalRepo.findAnimalBySpecies(species)
                     ) {
                    result.add(animal.toObject());
                }
                break;
            case "Бактерии":
                for (Bacteria bacteria: bacteriaRepo.findBacteriaBySpecies(species)
                ) {
                    result.add(bacteria.toObject());
                }
                break;

            case "Растения":
                for (Plant plant: plantRepo.findPlantBySpecies(species)
                ) {
                    result.add(plant.toObject());
                }
                break;
            case "Грибы":
                for (Shroom shroom: shroomRepo.findShroomBySpecies(species)
                ) {
                    result.add(shroom.toObject());
                }
                break;
            case "Вирусы":
                for (Virus virus: virusRepo.findVirusBySpecies(species)
                ) {
                    result.add(virus.toObject());
                }
                break;
        }
        return result;
    }
    public boolean register(AppUser user)
    {
        if (userRepo.findUserByeMail(user.geteMail())!=null)
            return false;
        else if (userRepo.findUserByLogin(user.getLogin())!=null)
            return false;
        user.setRole(roleRepo.findRoleByRoleName("Читатель"));
        userRepo.saveAndFlush(user);

        return true;
    }
}
