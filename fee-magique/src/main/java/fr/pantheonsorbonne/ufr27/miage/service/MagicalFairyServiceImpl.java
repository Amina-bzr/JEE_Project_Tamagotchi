package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.GiftDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.MagicalAlertDAO;
import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Random;

@ApplicationScoped
public class MagicalFairyServiceImpl implements MagicalFairyService {

    @Inject
    MagicalAlertDAO alertDAO;

    @Inject
    GiftDAO giftDAO;


    @Inject
    BankingService bankingService;

    @Inject
    AdoptionService adoptionService;

    @Override
    public void handleAlert(MagicalAlert alert) {
        alertDAO.saveAlert(alert);

        if ("neglect".equals(alert.getAlertType())) {
            removeNeglectedTamagotchi(alert.getTamagotchiId());
        } else {
            giveRandomGift(alert.getTamagotchiId());
        }
    }

    @Override
    public void giveRandomGift(Integer tamagotchiId) {
        Random random = new Random();
        int giftAmount = random.nextInt(50) + 1; // Cadeau aléatoire entre 1 et 50 Gotchi d'or

        // Créer un nouvel objet Gift
        GiftDAO gift = new Gift();
        gift.setTamagotchiId(tamagotchiId);
        gift.setGiftAmount(giftAmount);
        gift.setGiftTime(LocalDateTime.now());
        gift.setDescription("A magical gift from the Fairy!");

        // Enregistrer le cadeau
        giftDAO.createGift(gift);

        System.out.println("Gift of " + giftAmount + " Gotchi d'or given to Tamagotchi ID: " + tamagotchiId);
    }

    @Override
    public void removeNeglectedTamagotchi(Integer tamagotchiId) {
        adoptionService.removeTamagotchiFromOwner(tamagotchiId);
        System.out.println("Tamagotchi ID " + tamagotchiId + " retiré de son propriétaire pour négligence.");
    }
}
