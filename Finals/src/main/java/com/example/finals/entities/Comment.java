package com.example.finals.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="login")
    private User login;
    @NotBlank(message = "Текст комментария не может быть пустым")
    @Column(columnDefinition="TEXT")
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idRecipe")
    private Recipe recipe;
}
