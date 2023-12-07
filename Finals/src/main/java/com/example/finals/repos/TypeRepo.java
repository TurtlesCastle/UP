package com.example.finals.repos;

import com.example.finals.entities.DishCategory;
import com.example.finals.entities.DishType;
import com.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo  extends JpaRepository<DishType, Long> {
    public DishType findDishTypesByNameType(String nameType);
}
