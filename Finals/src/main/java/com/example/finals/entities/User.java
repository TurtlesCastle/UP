package com.example.finals.entities;

import com.example.finals.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="app_user")
public class User {
    public User(){}
    @Id
    @NotBlank(message = "Не заполнен логин")
    @Size(max=25, min=6, message = "Логин должен быть от 6 до 25 символов")
    private String login;
    @NotBlank(message = "Не установлен пароль")
    @Column(columnDefinition="TEXT")
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "login"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="license_code")
    private License license;

    @ManyToMany
    @JoinTable(name="favourite",
            joinColumns=@JoinColumn(name="login"),
            inverseJoinColumns=@JoinColumn(name="id_recipe"))

    private List<Recipe> recipes;

    @OneToMany(mappedBy = "id_comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
        this.license = null;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public License getLicense() {
        return license;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
