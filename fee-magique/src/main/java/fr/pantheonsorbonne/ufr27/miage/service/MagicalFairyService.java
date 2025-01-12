package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;

public interface MagicalFairyService {
    void handleAlert(AlertDTO alert);
    GiftDTO giveGift(GiftDTO gift);
}
