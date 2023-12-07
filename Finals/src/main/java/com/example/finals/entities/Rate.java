package com.example.finals.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rate;
    @NotBlank(message = "Поле оценки не может быть пустым")
    @Min(value = 1)
    @Max(value = 10)
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="login")
    private User rater;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idRecipe")
    private Recipe ratedRecipe;
}
