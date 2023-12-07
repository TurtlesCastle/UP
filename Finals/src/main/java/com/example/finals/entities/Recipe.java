package com.example.finals.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Check;

import java.util.List;
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecipe;

    @NotBlank(message = "Текст рецепта не может быть пустым")
    @Column(columnDefinition="TEXT")
    private String text;
    @NotBlank(message = "Рецепт должен иметь название")
    private String name;
    @ManyToMany
    @JoinTable(name="favourite",
            joinColumns=@JoinColumn(name="id_recipe"),
            inverseJoinColumns=@JoinColumn(name="login"))
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nameType")
    private DishType dishType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nameCategory")
    private DishCategory dishCategory;

    @OneToMany(mappedBy = "id_rate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rates;

    @OneToMany(mappedBy = "id_comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login")
    private User author;

    public Recipe(){}
    public Recipe(String text, String name)
    {
        this.text = text;
        this.name = name;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public DishType getDishType() {
        return dishType;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId_recipe() {
        return idRecipe;
    }
}
