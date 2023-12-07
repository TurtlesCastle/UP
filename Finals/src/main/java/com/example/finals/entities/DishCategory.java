package com.example.finals.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="dish_category")
public class DishCategory {
    @Id
    @Size(min=5, max=40, message = "Категория блюда должна быть от 5 до 40 символов")
    private String nameCategory;

    public DishCategory(){
    }
    public DishCategory(String name_category)
    {
        this.nameCategory = name_category;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String name_category) {
        this.nameCategory = name_category;
    }

    @OneToMany(mappedBy = "idRecipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes;
}
