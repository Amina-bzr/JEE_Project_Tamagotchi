package fr.pantheonsorbonne.ufr27.miage.model;

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
                query = "SELECT m FROM MagicalAlert m WHERE m.idTamagotchi = :idTamagotchi"
        )
})
public class MagicalAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idTamagotchi", nullable = false)
    private Integer idTamagotchi;

    @Column(name = "alertType", nullable = false)
    private String alertType;

    @Column(name = "alertTime", nullable = false)
    private LocalDateTime alertTime;

    public MagicalAlert() {
    }

    public MagicalAlert(Integer idTamagotchi, String alertType, LocalDateTime alertTime) {
        this.idTamagotchi = idTamagotchi;
        this.alertType = alertType;
        this.alertTime = alertTime;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTamagotchi() {
        return idTamagotchi;
    }

    public void setIdTamagotchi(Integer idTamagotchi) {
        this.idTamagotchi = idTamagotchi;
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

