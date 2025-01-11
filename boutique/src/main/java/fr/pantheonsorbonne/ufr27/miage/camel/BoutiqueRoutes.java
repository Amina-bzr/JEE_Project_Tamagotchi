package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.BindToRegistry;
import org.apache.camel.component.sjms2.Sjms2Component;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.inject.Inject;

@ApplicationScoped
public class BoutiqueRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;
    @Inject
    ProductGateway productGateway;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        // Consommateur pour la file "getProductsByCategoryRequest"
        from("sjms2:" + jmsPrefix + "getProductsByCategoryRequest")
                .unmarshal().json() // Convertir le message JSON en un objet ProductDTO
                .choice()
                // Si l'en-tête "category" est vide ou nul
                .when(header("category").isNull())
                .log("Aucune catégorie spécifiée, redirection vers tous les produits.")
                .bean(productGateway,"getAllProducts")
                .otherwise()
                .log("Requête reçue avec la catégorie : ${header.category}")
                .bean(productGateway, "getProductsByCategory(${header.category})"); // Appelle la méthode avec la catégorie


        // Consommateur pour la file "purchaseProductRequest"
        from("sjms2:" + jmsPrefix + "purchaseProductRequest")
                .unmarshal().json(ProductDTO.class)
                .bean(productGateway, "acheter");

        from("direct:confirmationAchat")
                .log("achat effectué avec succès")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "purchaseConfirmation") ;// Envoi à Boutique via SJMS2

    }




}
