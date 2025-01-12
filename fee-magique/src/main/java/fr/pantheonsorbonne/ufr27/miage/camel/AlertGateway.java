package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import fr.pantheonsorbonne.ufr27.miage.service.MagicalFairyService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class AlertGateway {

    @Inject
    CamelContext camelContext;

    @Inject
    MagicalFairyService magicalFairyService;

    // send gift (always goes to service bancaire)
    public void sendGift(TamagotchiDTO tamagotchi) {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            template.sendBody("direct:sendGift", tamagotchi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //creates the gift locally and sends it back
    public GiftDTO prepareGift(GiftDTO gift) {
        return this.magicalFairyService.giveGift(gift);
    }



    //send the tamogotchi whoose owner should be deleted to service adoption
    public TamagotchiDTO sendOwnerRemovalRequest(AlertDTO alert) {
        TamagotchiDTO tamagotchi = new TamagotchiDTO(alert.getIdTamagotchi());
        return tamagotchi;
    }
}
