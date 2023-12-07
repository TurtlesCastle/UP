package com.example.newdao;

public class Object {

    public int ID;
    public String col1;
    public String col2;
    public String col3;
    public String col4;


    public Animal toAnimal(){return new Animal(this.ID, this.col1, this.col2, this.col3, this.col4);}
    public Bacteria toBacteria(){return new Bacteria(ID, col1, col2, col3, col4);}
    public Plant toPlant(){return new Plant(ID, col1, col2, col3, col4);}
    public Shroom toShroom(){return new Shroom(ID, col1, col2, col3, col4);}
    public Virus toVirus(){return new Virus(ID, col1, col2, col3, col4);}
    public Object(String col1, String col2, String col3, String col4)
    {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
    }


}
