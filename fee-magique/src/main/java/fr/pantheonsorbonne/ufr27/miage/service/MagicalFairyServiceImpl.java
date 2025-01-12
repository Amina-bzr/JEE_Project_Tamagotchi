package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.AlertGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.GiftDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.MagicalAlertDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Gift;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class MagicalFairyServiceImpl implements MagicalFairyService {

    @Inject
    MagicalAlertDAO alertDAO;

    @Inject
    GiftDAO giftDAO;

    @Inject
    AlertGateway alertGateway;


    @Override
    public void handleAlert(AlertDTO alert) {
//        TamagotchiDTO tamagotchi = new TamagotchiDTO(alert.getIdTamagotchi());
//        if ("neglect".equals(alert.getAlertType())) { //alerte qui vient du service soins
//            // tamagotchi negligé (ex: mort) => informer service adoption pour retirer le tamagotchi de son proprietaire
//            adoptionGateway.sendOwnerRemovalRequest(tamagotchi);
//        } else { //other scenarios => gift
//            giveGift(alert.getIdTamagotchi(), null);
//        }
    }

    @Override
    public GiftDTO giveGift(GiftDTO gift) {
        Gift newGift = new Gift();
        newGift.setTamagotchiId(gift.getTamagotchiId());
        newGift.setGiftTime(LocalDateTime.now());
        newGift.setGiftAmount(gift.getGiftAmount());
        newGift.setDescription(gift.getDescription());
        giftDAO.createGift(newGift);
        System.out.println("MAGICAL FAIRY SERVICE : Gift of " + gift.getGiftAmount() + " Gotchi d'or given to Tamagotchi ID: " + gift.getTamagotchiId());
        return gift;
    }



}
