package com.example.newdao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="AppRole")
public class AppRole {
    @Transient
    private final int maxAppRoleSize = 25;

    @Id
    @Size(message="Поле должно содержать не более "+maxAppRoleSize+" символов", max=maxAppRoleSize)
    @NotBlank(message="Не указано название роли")
    @Column(unique = true, length = maxAppRoleSize)
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "role")
    private List<AppUser> users;

    public String getRoleName() {
        return roleName;
    }
}
