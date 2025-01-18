package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.camel.handlers.ProductAvailabilityHandler;
import fr.pantheonsorbonne.ufr27.miage.camel.handlers.ProductEnricher;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.CamelContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.inject.Inject;

@ApplicationScoped
public class CameRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;
    @Inject
    ProductGateway productGateway;

    @Inject
    ProductEnricher productEnricher;
    @Inject
    ProductAvailabilityHandler productAvailabilityHandler;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);



        //TO DO : Add exceptions

        from("sjms2:" + jmsPrefix + "getProductsFromBoutique?exchangePattern=InOut")
                .unmarshal().json() // Convertir le message JSON en un objet ProductDTO
                .choice()
                .when(header("category").isNull())
                    .log("Aucune catégorie spécifiée, redirection vers tous les produits.")
                    .bean(productGateway,"getAllProducts")
                .otherwise()
                    .log("Requête reçue avec la catégorie : ${header.category}")
                    .bean(productGateway, "getProductsByCategory(${header.category})") // Appelle la méthode avec la catégorie
                .end()
                .marshal().json();


        from("sjms2:" + jmsPrefix + "purchaseProductRequest?exchangePattern=InOut")
                .unmarshal().json(ProductDTO.class)
                .bean(productAvailabilityHandler)
                .choice()
                .when(header("available").isEqualTo(false))
                .marshal().json()
                .stop()
                .otherwise()
                .bean(productEnricher, "findProductPrice")
                .marshal().json()
                .end();



        from("sjms2:" + jmsPrefix + "purchaseProductConfirmation?exchangePattern=InOut")
                .unmarshal().json(ProductDTO.class)
                .bean(productGateway, "purchase")
                .bean(productEnricher, "findProductInfo")
                .marshal().json();

    }




}
