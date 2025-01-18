package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.InsufficientBalanceException;
import fr.pantheonsorbonne.ufr27.miage.model.Account;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

    public void purchaseProduct(Exchange exchange) {
        ProductDTO product = exchange.getMessage().getBody(ProductDTO.class);
        Account account = this.bankingService.getAccountByTamagotchi(product.getTamagotchiId());
        System.out.println("BANK: checking solde >? "+product.getPrice()+"...\n");
        try {
            this.bankingService.withdraw(account.getId(), product.getPrice());
            exchange.getIn().setHeader("sufficient", true);
            System.out.println("++++BANK: Sufficient solde!\n");
        } catch (InsufficientBalanceException e) {
            exchange.getIn().setHeader("sufficient", false);
            System.out.println("----BANK: Insufficient solde.\n");
        }
    }
}