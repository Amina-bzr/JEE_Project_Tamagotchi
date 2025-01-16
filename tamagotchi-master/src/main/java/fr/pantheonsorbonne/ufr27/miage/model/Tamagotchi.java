package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
        import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tamagotchi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTamagotchi", nullable = false)
    public Integer idTamagotchi;

    @ManyToOne
    @JoinColumn(name = "idOwner", nullable = true)
    public Owner owner;

    @Column(name = "name", nullable = false, length = 45)
    public String name;

    @Column(name = "hungry", nullable = false)
    public Integer hungry;

    @Column(name = "thirst", nullable = false)
    public Integer thirst;

    @Column(name = "happiness", nullable = false)
    public Integer happiness;

    @Column(name = "energy", nullable = false)
    public Integer energy;


    @Column(name = "state", nullable = false, length = 45, columnDefinition = "varchar(45) default 'good'")
    public String state; // "good", "sick", "dead"

    @Column(name = "disease", length = 45)
    public String disease; // null, "malnutrition", "obesity"

    @Column(name = "lastUpdateTime", nullable = false)
    public LocalDateTime lastUpdateTime; // Pour gérer les mises à jour automatiques

    @Column(name = "thresholdMin", nullable = false)
    public Integer thresholdMin = 10; // Seuil minimal pour tous les attributs

    @Column(name = "thresholdMax", nullable = false)
    public Integer thresholdMax = 100; // Seuil maximal pour tous les attributs

    public Tamagotchi() {

    }

    // Constructeur par défaut
    public Tamagotchi(Owner owner, String name) {
        this.owner = owner;
        this.name = name;
        this.state = "good";
        this.energy = 50;      // Début en état équilibré
        this.happiness = 50;
        this.hungry = 50;
        this.thirst = 50;
        this.lastUpdateTime = LocalDateTime.now(); // Initialisation au moment de la création
    }


    // Getters et Setters
    public Integer getIdTamagotchi() {
        return idTamagotchi;
    }

    public void setIdTamagotchi(Integer idTamagotchi) {
        this.idTamagotchi = idTamagotchi;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHungry() {
        return hungry;
    }

    public void setHungry(Integer hungry) {
        this.hungry = hungry;
    }

    public Integer getThirst() {
        return thirst;
    }

    public void setThirst(Integer thirst) {
        this.thirst = thirst;
    }

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


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getThresholdMin() {
        return thresholdMin;
    }

    public void setThresholdMin(Integer thresholdMin) {
        this.thresholdMin = thresholdMin;
    }

    public Integer getThresholdMax() {
        return thresholdMax;
    }

    public void setThresholdMax(Integer thresholdMax) {
        this.thresholdMax = thresholdMax;
    }




    // Méthode toString
    @Override
    public String toString() {
        return "Tamagotchi [idTamagotchi=" + idTamagotchi + ", owner=" + (owner != null ? owner.getIdOwner() : "null") +
                ", name=" + name + ", hungry=" + hungry + ", thirst=" + thirst +
                ", happiness=" + happiness + ", energy=" + energy +
                ", state=" + state + ", disease=" + disease + ", lastUpdateTime=" + lastUpdateTime +
                ", thresholdMin=" + thresholdMin + ", thresholdMax=" + thresholdMax + "]";
    }
}