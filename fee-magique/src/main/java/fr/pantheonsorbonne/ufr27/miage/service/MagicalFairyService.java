package fr.pantheonsorbonne.ufr27.miage.service;

public interface MagicalFairyService {
    void giveGiftToTamagotchi(Integer tamagotchiId, double amount);
    void giveRandomGift(Integer tamagotchiId);
    void sendMagicalAlert(String message);
    void removeNeglectedTamagotchi(Integer tamagotchiId);
}