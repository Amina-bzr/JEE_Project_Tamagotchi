package fr.pantheonsorbonne.ufr27.miage.dto;

import java.time.LocalDateTime;

public class GiftDTO {
    private Integer tamagotchiId;
    private Integer giftAmount; //Montant du cadeau en Gotchi d'or
    private String description;

    public GiftDTO() {}

    public GiftDTO(Integer tamagotchiId, Integer giftAmount, String description) {
        this.tamagotchiId = tamagotchiId;
        this.giftAmount = giftAmount;
        this.description = description;
    }

    public Integer getTamagotchiId() {
        return tamagotchiId;
    }

    public void setTamagotchiId(Integer tamagotchiId) {
        this.tamagotchiId = tamagotchiId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Integer giftAmount) {
        this.giftAmount = giftAmount;
    }
}