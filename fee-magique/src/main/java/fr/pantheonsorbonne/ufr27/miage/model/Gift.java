package fr.pantheonsorbonne.ufr27.miage.model;

import java.time.LocalDateTime;

public class Gift {
    private Integer id;
    private Integer tamagotchiId;
    private double amount;
    private LocalDateTime date;

    public Gift() {}

    public Gift(Integer tamagotchiId, double amount) {
        this.tamagotchiId = tamagotchiId;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTamagotchiId() {
        return tamagotchiId;
    }

    public void setTamagotchiId(Integer tamagotchiId) {
        this.tamagotchiId = tamagotchiId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
