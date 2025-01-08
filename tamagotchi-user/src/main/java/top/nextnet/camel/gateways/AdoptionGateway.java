package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.io.IOException;

@ApplicationScoped
public class AdoptionGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    public void createTamagothi(TamagotchiDTO tamagotchi) {
        try (ProducerTemplate producer = camelContext.createProducerTemplate()) {
            producer.sendBody("direct:TCreation", tamagotchi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    public void updateDonation(EDonation donation){
        this.donationService.updateDonation(donation);
    }
     */

}