package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTreatment", nullable = false)
    private Integer idTreatment;

    @Column(name = "name", nullable = false, length = 45)
    private String name; // ex. "Sirop", "Régime"

    @Column(name = "disease", nullable = false, length = 45)
    private String disease; // Maladie ciblée par le traitement

    @Column(name = "cost", nullable = false) // prix du traitement
    private Double cost;

    @Column(name = "effect", nullable = false) // effet du traitement sur les attributs du Tamagotchi
    private Integer effect; // Points ajoutés ou retirés (ex. +5, -10)

    // Constructeur pour l'initialisation
    public Treatment(String name, String disease, Double cost, Integer effect) {
        this.name = name;
        this.disease = disease;
        this.cost = cost;
        this.effect = effect;
    }

    // GETTERS ET SETTERS

    public Treatment() {}

    public Integer getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public String getName() {
        return name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "Treatment [idTreatment=" + idTreatment + ", name=" + name +
                ", disease=" + disease + ", cost=" + cost + ", effect=" + effect + "]";
    }
}

