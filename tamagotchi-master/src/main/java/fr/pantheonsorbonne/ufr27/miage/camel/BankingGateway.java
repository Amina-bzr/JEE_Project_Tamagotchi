package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Account;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import org.apache.camel.ProducerTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class BankingGateway {
    //fee magique tells adoption to remove tamagotchi from owner cause of death
    @Inject
    BankingService bankingService;

    public void depositGiftAmount(GiftDTO gift) {
        //deposit the gift
        Account account = this.bankingService.getAccountByTamagotchi(gift.getTamagotchiId());
        this.bankingService.deposit(account.getId(), gift.getGiftAmount());
    }
}