package com.example.newdao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="AppUser")
public class AppUser {
    @Transient
    private final int maxLoginSize = 25;
    @Transient
    private final int maxPasswordSize = 80;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userID;

    @Size(message="Поле должно содержать не более "+maxLoginSize+" символов", max=maxLoginSize)
    @NotBlank(message="Не указан логин")
    @Column(unique = true)
    private String login;

    @Size(message="Поле должно содержать не более "+maxPasswordSize+" символов", max=maxPasswordSize)
    @NotBlank(message="Не указан пароль")
    private String password;

    @NotBlank(message="Не указан адрес электронной почты")
    @Column(unique = true)
    private String eMail;



    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="role", referencedColumnName = "roleName")
    private AppRole role;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String geteMail() {
        return eMail;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

    public AppUser(){}
    public AppUser(String login, String eMail, String password)
    {
        this.login = login;
        this.eMail = eMail;
        this.password = password;
    }


}
