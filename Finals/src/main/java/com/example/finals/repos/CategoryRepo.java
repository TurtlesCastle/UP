package com.example.finals.repos;

import com.example.finals.entities.DishCategory;
import com.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<DishCategory, Long> {
    public DishCategory findDishCategoriesByNameCategory(String name_category);
}
