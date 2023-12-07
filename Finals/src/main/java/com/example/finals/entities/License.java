package com.example.finals.entities;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "app_license")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int license_code;
    @NotBlank(message = "Обязательно должна присутствовать ссылка на лицензию")
    @Column(columnDefinition="TEXT")
    private String text_source;
    @NotBlank(message = "Обязательно должны присутствовать ФИО")
    private String fio;

    public License(){
    }
    public License(String text_source, String fio)
    {
        this.text_source = text_source;
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public String getText_source() {
        return text_source;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setText_source(String text_source) {
        this.text_source = text_source;
    }
}
