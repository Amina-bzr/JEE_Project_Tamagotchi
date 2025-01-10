package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.GiftDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.MagicalAlertDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Gift;
import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.time.LocalDateTime;
import java.util.Random;

@ApplicationScoped
public class MagicalFairyServiceImpl implements MagicalFairyService {

    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    GiftDAO giftDAO;

    @Override
    public void giveGiftToTamagotchi(Integer tamagotchiId, double amount) {
        Gift gift = new Gift(tamagotchiId, amount);
        giftDAO.createGift(gift);

        // Envoyer un message via JMS
        producerTemplate.sendBody("direct:sendGift", gift);

        System.out.println("Cadeau offert au Tamagotchi ID: " + tamagotchiId + " d'un montant de: " + amount);
    }

    @Override
    public void sendMagicalAlert(String message) {
        MagicalAlert alert = new MagicalAlert(message);

        // Envoyer une alerte magique via JMS
        producerTemplate.sendBody("direct:sendAlert", alert);

        System.out.println("Alerte magique envoyée : " + message);
    }

    @Override
    public void giveRandomGift(Integer tamagotchiId) {
        Random random = new Random();
        int giftAmount = random.nextInt(50) + 1; // Cadeau aléatoire entre 1 et 50 Gotchi d'or

        // Créer un nouvel objet Gift
        Gift gift = new Gift();
        gift.setTamagotchiId(tamagotchiId);
        gift.setAmount(giftAmount);


        // Enregistrer le cadeau
        giftDAO.createGift(gift);

        System.out.println("Gift of " + giftAmount + " Gotchi d'or given to Tamagotchi ID: " + tamagotchiId);
    }

    @Override
    public void removeNeglectedTamagotchi(Integer tamagotchiId) {
        producerTemplate.sendBody("direct:removeNeglectedTamagotchi", tamagotchiId);
        System.out.println("Tamagotchi ID " + tamagotchiId + " retiré de son propriétaire pour négligence.");
    }
}
