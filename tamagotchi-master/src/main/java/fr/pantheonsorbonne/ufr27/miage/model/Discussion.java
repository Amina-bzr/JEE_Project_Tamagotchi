package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discussionId;

    @OneToMany(mappedBy = "discussion")
    private List<Interaction> interactions; // Les interactions faisant partie de cette discussion

    // Constructors
    public Discussion() {}

    // Getters and Setters
    public Integer getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }
}