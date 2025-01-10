package fr.pantheonsorbonne.ufr27.miage.model;

import fr.pantheonsorbonne.ufr27.miage.dto.Owner;
import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MagicalAlert")
@NamedQueries({
        @NamedQuery(
                name = "MagicalAlert.findAll",
                query = "SELECT m FROM MagicalAlert m"
        ),
        @NamedQuery(
                name = "MagicalAlert.findByType",
                query = "SELECT m FROM MagicalAlert m WHERE m.alertType = :alertType"
        ),
        @NamedQuery(
                name = "MagicalAlert.findByTamagotchi",
                query = "SELECT m FROM MagicalAlert m WHERE m.tamagotchi.id = :tamagotchiId"
        )
})
public class MagicalAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTamagotchi", nullable = false)
    private Tamagotchi tamagotchi;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idOwner", nullable = false)
    private Owner owner;

    @Column(name = "alertType", nullable = false)
    private String alertType;

    @Column(name = "alertTime", nullable = false)
    private LocalDateTime alertTime;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }

    public void setTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public LocalDateTime getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(LocalDateTime alertTime) {
        this.alertTime = alertTime;
    }
}

