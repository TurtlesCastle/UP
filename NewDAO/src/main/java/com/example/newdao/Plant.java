package com.example.newdao;

import com.example.newdao.Object;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.BindingResult;

@Entity
@Table(name="Plant")
public class Plant{
    @Transient
    private final int maxGenusSize = 25;
    @Transient
    private final int maxSpeciesSize = 80;
    @Transient
    private final int maxMethodOfNutritionSize = Integer.MAX_VALUE;
    @Transient
    private final int maxFamilySize = 35;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;
    @Size(message="Поле Род должно содержать не более "+maxGenusSize+" символов", max=maxGenusSize)
    @NotBlank(message="Не указан род")
    private String genus;
    @Size(message="Поле Вид должно содержать не более "+maxSpeciesSize+" символов", max=maxSpeciesSize)
    @NotBlank(message="Не указан вид")
    @Column(unique = true)
    private String species;
    @Size(message="Поле Способ питания должно содержать не более "+maxMethodOfNutritionSize+" символов", max=maxMethodOfNutritionSize)
    private String methodOfNutrition;
    @Size(message="Поле Семейство должно содержать не более "+maxFamilySize+" символов", max=maxFamilySize)
    @NotBlank(message="Не указано семейство")
    private String family;


    public Object toObject()
    {
        Object object = new Object(this.genus, this.species, this.methodOfNutrition, this.family);
        object.ID = this.ID;
        return object;
    }
    public Plant(int id, String genus, String species, String methodOfNutrition, String family )
    {
        this.ID = id;
        this.genus = genus;
        this.species = species;
        this.methodOfNutrition = methodOfNutrition;
        this.family = family;

    }
    public  Plant(){}


}
