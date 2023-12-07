package com.example.finals.service;

import com.example.finals.entities.*;
import com.example.finals.enums.Role;
import com.example.finals.repos.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MyService {
    private final UserRepo userRepo;
    private final RecipeRepo recipeRepo;
    private final CategoryRepo categoryRepo;
    private final TypeRepo typeRepo;
    private final CommRepo commRepo;
    private final RateRepo rateRepo;
    private final LicenseRepo licenseRepo;

    public MyService(UserRepo userRepo, RecipeRepo recipeRepo, CategoryRepo categoryRepo
    ,TypeRepo typeRepo,CommRepo commRepo,RateRepo rateRepo, LicenseRepo licenseRepo){
        this.userRepo = userRepo;
        this.recipeRepo = recipeRepo;
        this.categoryRepo = categoryRepo;
        this.typeRepo = typeRepo;
        this.commRepo = commRepo;
        this.rateRepo = rateRepo;
        this.licenseRepo = licenseRepo;
    }
    public List<User> getUserRows()
    {
        return userRepo.findAll();
    }

    public boolean register(User user)
    {
        if (userRepo.findUserByLogin(user.getLogin())!=null)
            return false;
        user.setRoles(Collections.singleton(Role.Guest));
        userRepo.saveAndFlush(user);

        return true;
    }
    public void addRecipe(Recipe recipe, String category_name, String type_name)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        recipe.setAuthor(userRepo.findUserByLogin(currentPrincipalName));
        recipe.setDishType(typeRepo.findDishTypesByNameType(type_name));
        recipe.setDishCategory(categoryRepo.findDishCategoriesByNameCategory(category_name));
        recipeRepo.saveAndFlush(recipe);
    }
    public List<Recipe> getRecipes()
    {
        return recipeRepo.findAll();
    }
    public List<DishCategory> getCategories(){return categoryRepo.findAll();}
    public List<DishType> getTypes(){return typeRepo.findAll();}
    public void addLicense(License license)
    {
        licenseRepo.saveAndFlush(license);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User cur_user = userRepo.findUserByLogin(currentPrincipalName);
        cur_user.setLicense(licenseRepo.findByFio(license.getFio()));
        userRepo.saveAndFlush(cur_user);
    }
    public void addCategory(String category)
    {
        categoryRepo.saveAndFlush(new DishCategory(category));
    }
    public void deleteCategory(String category)
    {
        categoryRepo.delete(categoryRepo.findDishCategoriesByNameCategory(category));
    }
    public void addType(String type)
    {
        typeRepo.saveAndFlush(new DishType(type));
    }
    public void deleteType(String type)
    {
        typeRepo.delete(typeRepo.findDishTypesByNameType(type));
    }
    public List<Recipe> getMyRecipes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Recipe> recipes = recipeRepo.findAll();
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe: recipes
             ) {
            if (recipe.getAuthor().getLogin().equals(currentPrincipalName))
                result.add(recipe);
        }
        return result;
    }
    public void addFavourite(int ID)
    {
        Recipe recipe = recipeRepo.findByIdRecipe(ID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findUserByLogin(currentPrincipalName);
        user.getRecipes().add(recipe);
        userRepo.saveAndFlush(user);
    }

}
