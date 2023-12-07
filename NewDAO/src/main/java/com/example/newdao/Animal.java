package com.example.newdao;


import ch.qos.logback.core.joran.sanity.Pair;
import com.example.newdao.Object;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.BindingResult;

@Entity
@Table(name="Animal")
public class Animal{
    @Transient
    private final int maxGenusSize = 25;
    @Transient
    private final int maxSpeciesSize = 80;
    @Transient
    private final int maxAreaOfLivingSize = Integer.MAX_VALUE;
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
    @Size(message="Поле Ареал распространения должно содержать не более "+maxAreaOfLivingSize+" символов", max=maxAreaOfLivingSize) //На случай, если когда-нибудь изменится
    private String areaOfLiving;
    @Size(message="Поле Семейство должно содержать не более "+maxFamilySize+" символов", max=maxFamilySize)
    @NotBlank(message="Не указано семейство")
    private String family;

    public Animal(){

    }
    public Animal(int id, String genus, String species, String areaOfLiving, String family )
    {
        this.ID = id;
        this.genus = genus;
        this.species = species;
        this.areaOfLiving = areaOfLiving;
        this.family = family;

    }
    public Object toObject()
    {
        Object object = new Object(this.genus, this.species, this.areaOfLiving, this.family);
                object.ID = this.ID;
        return object;
    }


}
