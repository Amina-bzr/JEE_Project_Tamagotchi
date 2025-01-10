package fr.pantheonsorbonne.ufr27.miage.camel;


//import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi; u cant so reference it with full path
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import fr.pantheonsorbonne.ufr27.miage.service.AdoptionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class AdoptionGateway {
    @Inject
    AdoptionService AdoptionService;

    @Inject
    CamelContext camelContext;

    //fee magique tells adoption to remove tamagotchi from owner cause of death
    public void removeTamagotchiFromOwner(TamagotchiDTO tamagotchi) {
        this.AdoptionService.updateTamagotchiOwner(tamagotchi.getId(), null);
    }

    //adoption sends alert to fee magique to tell her that the user adopted a new tamagotchi
    public void sendAdoptedTamagotchi(TamagotchiDTO tamagotchi) {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            template.sendBody("direct:TamagotchiAdopted", tamagotchi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Tamagotchi receives a gift => service bancaire (money)

    //increase bonheur: réception cadeau, manger, achat de boutique, ...

}