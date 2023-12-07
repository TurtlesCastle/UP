package com.example.finals.repos;

import com.example.finals.entities.Recipe;
import com.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepo  extends JpaRepository<Recipe, Long> {
    public Recipe findByIdRecipe(int idRecipe);
}
