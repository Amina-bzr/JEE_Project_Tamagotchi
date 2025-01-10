package fr.pantheonsorbonne.ufr27.miage.dto;

public class TamagotchiDTO {
    Integer id;
    String name;
    Integer owner;
    String state;


    public TamagotchiDTO(Integer id, String state){
        this.id = id;
        this.state = state;
        this.name = "";
        this.owner = null;
    }

    public TamagotchiDTO(Integer id){
        this.id = id;
        this.name = "";
        this.state = null;
        this.owner = null;
    }

    public TamagotchiDTO(String name, Integer owner){
        this.id=null;
        this.state = null;
        this.name = name;
        this.owner = owner;
    }

    public TamagotchiDTO(String name, Integer owner, Integer id){
        this.id=id;
        this.state = null;
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

    public String getState() {
        return state;
    }

}