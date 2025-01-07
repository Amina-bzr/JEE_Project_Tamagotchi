package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;

public interface MagicalFairyService {
    void handleAlert(MagicalAlert alert);
    void giveRandomGift(Integer tamagotchiId);
    void removeNeglectedTamagotchi(Integer tamagotchiId);
}
