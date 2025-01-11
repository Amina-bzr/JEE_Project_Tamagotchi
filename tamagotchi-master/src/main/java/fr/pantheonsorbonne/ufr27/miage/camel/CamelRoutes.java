package fr.pantheonsorbonne.ufr27.miage.camel;


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
    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        from("sjms2:" + jmsPrefix + "removeTamagotchiFromOwner")
                .unmarshal().json(TamagotchiDTO.class)
                .bean(adoptionGateway, "removeTamagotchiFromOwner");


        from("direct:getAllProductsFromBoutique")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "getAllProductsRequest") ;// Envoi à Boutique via SJMS2

        from("direct:getProductsByCategoryFromBoutique")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "getProductsByCategoryRequest") ;// Envoi à Boutique via SJMS2


        from("direct:achat")
                .log("demande achat envoyée")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "purchaseProductRequest") ;// Envoi à Boutique via SJMS2


        from ("sjms2:" + jmsPrefix + "purchaseConfirmation")
                .unmarshal().json(ProductDTO.class)
                .bean(boutiqueGateway, "saveToInventory");


        //SERVICE ADOPT -----> FEE
        from("direct:TamagotchiAdopted")//please give him a gift !
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "TamagotchiAdopted");

    }

}
