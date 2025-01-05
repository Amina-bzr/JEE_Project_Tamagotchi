package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interactionId;

    @Column(nullable = false)
    private String type; // Ex : "parler", "jouer"

    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private Owner initiator; // L'initiateur de l'interaction

    @ManyToOne
    @JoinColumn(name = "discussionId")
    private Discussion discussion; // La discussion associée à l'interaction

    // Constructors
    public Interaction() {}

    public Interaction(String type, Owner initiator, Discussion discussion) {
        this.type = type;
        this.initiator = initiator;
        this.discussion = discussion;
    }

    // Getters and Setters
    public Integer getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(Integer interactionId) {
        this.interactionId = interactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Owner getInitiator() {
        return initiator;
    }

    public void setInitiator(Owner initiator) {
        this.initiator = initiator;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }
}
