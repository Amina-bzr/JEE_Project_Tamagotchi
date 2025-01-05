package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Tamagotchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTamagotchi", nullable = false)
    private Integer idTamagotchi;

    // Getters and Setters
    public Integer getIdTamagotchi() {
        return idTamagotchi;
    }

    public void setIdTamagotchi(Integer idTamagotchi) {
        this.idTamagotchi = idTamagotchi;
    }


    @ManyToOne
    @JoinColumn(name = "idOwner", nullable = true)
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "hungry", nullable = false)
    private Integer hungry;

    public Integer getHungry() {
        return hungry;
    }

    public void setHungry(Integer hungry) {
        this.hungry = hungry;
    }

    @Column(name = "happiness", nullable = false)
    private Integer happiness;

    @Column(name = "energy", nullable = false)
    private Integer energy;

    @Column(name = "health", nullable = false)
    private Integer health;

    @Column(name = "state", nullable = false, length = 45, columnDefinition = "varchar(45) default 'good'")
    private String state; // can be good, sick, dead









    public Integer getHappiness() {
        return happiness;
    }

    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Default constructor
    public Tamagotchi() {
        this.state = "good"; //default state is set t good
        this.energy=100;
        this.happiness=100;
        this.health=100;
        this.hungry=0;
    }

    // toString method for better readability
    @Override
    public String toString() {
        return "Tamagotchi [idTamagotchi=" + idTamagotchi + ", owner=" + owner + ", name=" + name +
                ", hungry=" + hungry + ", happiness=" + happiness + ", energy=" + energy +
                ", health=" + health + ", state=" + state + "]";
    }
}
