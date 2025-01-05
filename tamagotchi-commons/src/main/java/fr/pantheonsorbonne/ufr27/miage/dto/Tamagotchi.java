package fr.pantheonsorbonne.ufr27.miage.dto;

public class Tamagotchi {
    String name;
    Owner owner;

    public Tamagotchi(String name, Owner owner){
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }
}