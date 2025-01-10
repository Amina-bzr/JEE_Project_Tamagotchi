package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;
import fr.pantheonsorbonne.ufr27.miage.service.MagicalFairyService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class MagicalFairyRoutes extends RouteBuilder {

    @Inject
    MagicalFairyService magicalFairyService;

    @Override
    public void configure() throws Exception {
        from("jms:queue:healthAlerts")
                .unmarshal().json(MagicalAlert.class)
                .bean(magicalFairyService, "handleAlert(${body})")
                .log("Handled alert for Tamagotchi ID: ${body.tamagotchiId}");
    }
}