package com.example.finals.repos;

import com.example.finals.entities.Rate;
import com.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo  extends JpaRepository<Rate, Long> {
}
