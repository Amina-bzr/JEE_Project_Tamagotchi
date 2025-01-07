package fr.pantheonsorbonne.ufr27.miage.dto;

public class Tamagotchi {
    Integer id;
    String name;
    Integer owner;


    public Tamagotchi(Integer id){
        this.id = id;
        this.name = "";
        this.owner = null;
    }

    public Tamagotchi(String name, Integer owner){
        this.id=null;
        this.name = name;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOwner() {
        return owner;
    }
}