package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private Owner recipient; // Le destinataire de la notification

    // Constructors
    public Notification() {}

    public Notification(String message, Owner recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    // Getters and Setters
    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Owner getRecipient() {
        return recipient;
    }

    public void setRecipient(Owner recipient) {
        this.recipient = recipient;
    }
}
