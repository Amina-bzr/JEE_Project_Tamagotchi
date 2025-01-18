package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotification", nullable = false) // id de la notif
    private Integer idNotification;

    @ManyToOne
    @JoinColumn(name = "idTamagotchi", nullable = false) //  tamagotchi concerne par la notif
    private Tamagotchi tamagotchi;

    @Column(name = "type", nullable = false, length = 45) // type de notif
    private String type; // ex. "besoin", "maladie", "décès"

    @Column(name = "content", nullable = false) // message de la notif
    private String content;

    @Column(name = "status", nullable = false, length = 45) // statut de la notif
    private String status; // ex. "lu","en attente"

    @Column(name = "creationTime", nullable = false) // date de creation/maj de la notif
    private LocalDateTime creationTime;

    // GETTERS ET SETTERS

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }

    public void setTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Notification [idNotification=" + idNotification + ", tamagotchi=" + tamagotchi.getIdTamagotchi() +
                ", type=" + type + ", content=" + content + ", status=" + status +
                ", creationTime=" + creationTime + "]";
    }
}
