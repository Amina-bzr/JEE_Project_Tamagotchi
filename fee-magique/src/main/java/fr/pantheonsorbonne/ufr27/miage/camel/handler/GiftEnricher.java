package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertTypes;
import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.service.MagicalFairyService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Random;

//finds the gift amount from the alert based on its type and returns the giftDTO
@ApplicationScoped
public class GiftEnricher {
    public GiftDTO findGiftAmount(AlertDTO alert) {
        if (alert.getAlertType().equals(AlertTypes.CREATED)) return new GiftDTO(alert.getIdTamagotchi(),150,"Welcome to the world! A special gift from the magical fairy~!");
        if (alert.getAlertType().equals(AlertTypes.ADOPTED)) return new GiftDTO(alert.getIdTamagotchi(),300,"A kind soul has adopted our lonely tamagotchi, a new life awaits you! A special gift from the magical fairy~!");
        Random random = new Random();
        return new GiftDTO(alert.getIdTamagotchi(),random.nextInt(50) + 1,"Congrats! You've got a special gift from the magical fairy~!");
    }
}
