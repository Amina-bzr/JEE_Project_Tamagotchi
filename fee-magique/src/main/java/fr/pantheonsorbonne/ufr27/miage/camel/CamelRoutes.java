package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.camel.handler.GiftEnricher;
import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;


import fr.pantheonsorbonne.ufr27.miage.dto.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    AlertGateway alertGateway;

    @Inject
    GiftEnricher enricher;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        //*********************ADOPTION SERVICE COMM
            //adopted tamagotchi
        from("sjms2:" + jmsPrefix + "TamagotchiAdopted")
                .unmarshal().json(TamagotchiDTO.class)
                .bean(alertGateway, "sendGift")
                .log("MAGICAL FAIRY: Received gift request for adopted tamagotchi ${body.idTamagotchi} from ADOPTION SERVICE");

            //sending owner removal request to adoption service
        from("direct:RemoveOwner")
                .marshal().json(TamagotchiDTO.class)
                .to("sjms2:" + jmsPrefix + "RemoveOwner")
                .log("MAGICAL FAIRY: sent owner removal request for neglected tamagotchi ${body.id} to ADOPTION SERVICE.");

        //*********************ALERT
            //process received alert
        from("sjms2:" + jmsPrefix + "sendAlert")
                .unmarshal().json(AlertDTO.class)
                .choice()
                .when(simple("${body.alertType} == 'neglect'"))
                    .log("MAGICAL FAIRY: Received neglection alert from SERVICE SOINS.")
                    .bean(alertGateway, "sendOwnerRemovalRequest") //generates the tamagotchiDTO
                    .marshal().json(TamagotchiDTO.class)
                    .to("sjms2:" + jmsPrefix + "removeTamagotchiFromOwner")//Send to adoptionService
                .otherwise() //it's a gift request!
                    .bean(enricher, "findGiftAmount") //finds the gift amount and returns the giftDTO
                    .bean(alertGateway, "prepareGift") //saves the gift locally and returns the gift dto
                    .marshal().json(GiftDTO.class)
                    .to("sjms2:" + jmsPrefix + "sendGift")
                .end();


//            from("jms:queue:healthAlerts")
//                    .unmarshal().json(MagicalAlert.class)
//                    .bean(magicalFairyService, "handleAlert(${body})")
//                    .log("Handled alert for Tamagotchi ID: ${body.tamagotchiId}");
    }


    //alert : tamagotchi neglected, adopted, created.
    //giftAmount :
    //          when a tamagotchi is created : 150
    //          when a tamagotchi is adopted : 200
}   //          otherwise : Random
