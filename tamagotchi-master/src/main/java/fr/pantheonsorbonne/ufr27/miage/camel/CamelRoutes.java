package fr.pantheonsorbonne.ufr27.miage.camel;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.BankingGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.BoutiqueGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.ufr27.miage.camel.handlers.ResponseMessageHandler;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    AdoptionGateway adoptionGateway;

    @Inject
    BoutiqueGateway boutiqueGateway;
    @Inject
    BankingGateway bankingGateway;

    @Inject
    ResponseMessageHandler responseMessageHandler;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);


        //*******************************ADOPTION*******************************
            //Request from magical fairy : remove tamagotchi from owner
        from("sjms2:" + jmsPrefix + "removeTamagotchiFromOwner")
                .unmarshal().json(TamagotchiDTO.class)
                .bean(adoptionGateway, "removeTamagotchiFromOwner");

            //SERVICE ADOPT -----> FEE : Tamagotchi got adopted/created!
        from("direct:AdoptionAlert")
                .marshal().json(AlertDTO.class)
                .to("sjms2:" + jmsPrefix + "sendAlert");

        //********************************BANKING********************************
        //SEGGESTION: make different types of gifts! money, item (to inventory) => choice
        from("sjms2:" + jmsPrefix + "sendGift")
                .unmarshal().json(GiftDTO.class)
                .bean(bankingGateway, "depositGiftAmount");


        //*******************************Boutique*******************************
        from("direct:getProductsFromBoutique")
                .marshal().json() // Marshal the input to JSON
                .to("sjms2:" + jmsPrefix + "getProductsFromBoutique?exchangePattern=InOut")
                .unmarshal(new ListJacksonDataFormat(ProductDTO.class));


        from("direct:achat")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "purchaseProductRequest?exchangePattern=InOut")
                .unmarshal().json(ProductDTO.class)
                .choice()
                .when(header("available").isEqualTo(true))
                    .bean(bankingGateway, "purchaseProduct")
                    .choice()
                    .when(header("sufficient").isEqualTo(false))
                        .bean(responseMessageHandler)
                        .marshal().json()
                        .log("Available and unsufficient money.")
                    .stop()
                    .otherwise()
                        .marshal().json()
                        .to("sjms2:" + jmsPrefix + "purchaseProductConfirmation?exchangePattern=InOut")
                        .unmarshal().json(ProductDTO.class)
                        .bean(boutiqueGateway, "saveToInventory")
                        .bean(responseMessageHandler)
                        .marshal().json()
                        .log("Available and sufficient money.")
                    .endChoice()
                .stop()
                .otherwise()
                    .bean(responseMessageHandler)
                    .marshal().json()
                .endChoice()
                .end();

    }

}
