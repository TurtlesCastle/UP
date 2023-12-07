package com.example.newdao;

import com.example.newdao.Object;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.BindingResult;

@Entity
@Table(name="Bacteria")
public class Bacteria{
    @Transient
    private final int maxFormSize = 20;
    @Transient
    private final int maxSpeciesSize = 80;
    @Transient
    private final int maxAreaOfLivingSize = Integer.MAX_VALUE;
    @Transient
    private final int maxSize = Integer.MAX_VALUE;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;
    @Size(message="Поле Форма должно содержать не более "+maxFormSize+" символов", max=maxFormSize)
    @NotBlank(message="Не указана форма")
    private String form;
    @Size(message="Поле Вид должно содержать не более "+maxSpeciesSize+" символов", max=maxSpeciesSize)
    @NotBlank(message="Не указан вид")
    @Column(unique = true)
    private String species;
    @Size(message="Поле Место обитания должно содержать не более "+maxAreaOfLivingSize+" символов", max=maxAreaOfLivingSize)
    private String areaOfLiving;
    @Size(message="Поле Размер должно содержать не более "+maxSize+" символов", max=maxSize)
    private String size;

    public Object toObject()
    {
        Object object = new Object(this.form, this.species, this.areaOfLiving, this.size);
        object.ID = this.ID;
        return object;
    }

    public Bacteria(int id, String form, String species, String areaOfLiving, String size )
    {
        this.ID = id;
        this.form = form;
        this.species = species;
        this.areaOfLiving = areaOfLiving;
        this.size = size;
    }
    public Bacteria(){}



}
