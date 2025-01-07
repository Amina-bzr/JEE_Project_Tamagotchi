package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Gift")
@NamedQueries({
        @NamedQuery(
                name = "Gift.findAll",
                query = "SELECT g FROM Gift g"
        ),
        @NamedQuery(
                name = "Gift.findByTamagotchi",
                query = "SELECT g FROM Gift g WHERE g.tamagotchiId = :tamagotchiId"
        ),
        @NamedQuery(
                name = "Gift.findByDateRange",
                query = "SELECT g FROM Gift g WHERE g.giftTime BETWEEN :startDate AND :endDate"
        )
})
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idTamagotchi", nullable = false)
    private Integer tamagotchiId;

    @Column(name = "giftAmount", nullable = false)
    private Integer giftAmount; // Montant du cadeau en Gotchi d'or

    @Column(name = "giftTime", nullable = false)
    private LocalDateTime giftTime; // Date et heure du cadeau

    @Column(name = "description")
    private String description; // Une description ou un message accompagnant le cadeau

    // Getters et Setters
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

    public Integer getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Integer giftAmount) {
        this.giftAmount = giftAmount;
    }

    public LocalDateTime getGiftTime() {
        return giftTime;
    }

    public void setGiftTime(LocalDateTime giftTime) {
        this.giftTime = giftTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
