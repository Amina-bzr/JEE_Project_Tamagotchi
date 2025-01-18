package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Notification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class SoinGateway {

    @Inject
    CamelContext camelContext;

     /**
     * Notifier la fée magique via Camel.
     * @param alert La notification à envoyer.
     */
    public void notifyFairy(AlertDTO alert) { // (AlertDTO alert, Integer idTamagotchi, AlertTypes.DEAD)
        try (ProducerTemplate producer = camelContext.createProducerTemplate()) {
            producer.sendBody("direct:SoinAlert", alert);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la notification à la fée magique : " + e.getMessage(), e);
        }
    }

}
