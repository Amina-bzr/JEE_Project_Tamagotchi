package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.GiftDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;



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

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        //*******************************ADOPTION*******************************
        //Request from magical fairy : remove tamagotchi from owner
        from("sjms2:" + jmsPrefix + "removeTamagotchiFromOwner")
                .unmarshal().json(TamagotchiDTO.class)
                .log("ADOPTION SERVICE: Received owner removal request for tamagotchi ${body.idTamagotchi} from MAGICAL FEE ")
                .bean(adoptionGateway, "removeTamagotchiFromOwner")
                .log("ADOPTION SERVICE: Tamagotchi removed from owner.");

        //SERVICE ADOPT -----> FEE : Tamagotchi got adopted/created!
        from("direct:AdoptionAlert")
                .marshal().json(AlertDTO.class)
                .to("sjms2:" + jmsPrefix + "sendAlert")
                .log("ADOPTION SERVICE: Alert for adopted/created tamagotchi sent to MAGICAL FAIRY.");

        //********************************BANKING********************************
        //SEGGESTION: make different types of gifts! money, item (to inventory) => choice
        from("sjms2:" + jmsPrefix + "sendGift")
                .unmarshal().json(GiftDTO.class)
                .bean(bankingGateway, "depositGiftAmount");


        //*******************************Boutique*******************************
        from("direct:getAllProductsFromBoutique")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "getAllProductsRequest")
                .log("MASTER: Sent GET ALL products request to BOUTIQUE.");

        from("direct:getProductsByCategoryFromBoutique")
                .marshal().json()
                .log("MASTER: Sending GET products by category ${header.category} request to BOUTIQUE.")
                .to("sjms2:" + jmsPrefix + "getProductsByCategoryRequest");


        from("direct:achat")
                .marshal().json(ProductDTO.class)
                .log("MASTER: Sent purchase request for product ${body.productId} to BOUTIQUE.")
                .to("sjms2:" + jmsPrefix + "purchaseProductRequest");

        //TO DO: add gateway between bank and boutique

        from ("sjms2:" + jmsPrefix + "purchaseConfirmation")
                .unmarshal().json(ProductDTO.class)
                .log("MASTER: Received purchase confirmation for product ${body.productId} from BOUTIQUE.")
                .bean(boutiqueGateway, "saveToInventory");




    }

}