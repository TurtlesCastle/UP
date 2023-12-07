package com.example.newdao;

import com.example.newdao.Object;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.BindingResult;

@Entity
@Table(name="Virus")
public class Virus{
    @Transient
    private final int maxClassificationSize = 35;
    @Transient
    private final int maxSpeciesSize = 80;
    @Transient
    private final int maxCarrierSize = Integer.MAX_VALUE;
    @Transient
    private final int maxSymptomsSize = Integer.MAX_VALUE;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;
    @Size(message="Поле Классификация должно содержать не более "+maxClassificationSize+" символов", max=maxClassificationSize)
    @NotBlank(message="Не указан род")
    private String classification;
    @Size(message="Поле Вид должно содержать не более "+maxSpeciesSize+" символов", max=maxSpeciesSize)
    @NotBlank(message="Не указан вид")
    @Column(unique = true)
    private String species;
    @Size(message="Поле Носитель должно содержать не более "+maxCarrierSize+" символов", max=maxCarrierSize)
    @NotBlank(message="Не указан носитель")
    private String carrier;
    @Size(message="Поле Симптомы должно содержать не более "+maxSymptomsSize+" символов", max=maxSymptomsSize)
    private String symptoms;


    public Object toObject()

    {
        Object object = new Object(this.classification, this.species, this.carrier, this.symptoms);
        object.ID = this.ID;
        return object;
    }
    public Virus(int id, String classification, String species, String carrier, String existenceTime )
    {
        this.ID = id;
        this.classification = classification;
        this.species = species;
        this.carrier = carrier;
        this.symptoms = existenceTime;

    }
    public Virus(){

    }

}
