package com.example.finals.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="dish_type")
public class DishType {
    @Id
    @Size(min=5, max=40, message = "Тип блюда должна быть от 5 до 40 символов")
    private String nameType;

    public DishType(){
    }
    public DishType(String name_type)
    {
        this.nameType = name_type;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String name_category) {
        this.nameType = name_category;
    }

    @OneToMany(mappedBy = "idRecipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes;
}
