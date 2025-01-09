package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.builder.RouteBuilder;
import fr.pantheonsorbonne.ufr27.miage.service.BoutiqueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BoutiqueRoutes extends RouteBuilder {

    @Inject
    private BoutiqueService boutiqueService;

    @Override
    public void configure() throws Exception {
        // Route pour récupérer tous les produits
        from("direct:getAllProducts")
                .process(exchange -> {
                    exchange.getMessage().setBody(boutiqueService.getAllProducts());
                });

        // Route pour récupérer les produits par catégorie
        from("direct:getProductsByCategory")
                .process(exchange -> {
                    String category = exchange.getIn().getHeader("category", String.class);
                    exchange.getMessage().setBody(boutiqueService.getProductsByCategory(category));
                });

        // Route pour traiter une demande d'achat
        from("direct:purchaseProduct")
                .process(exchange -> {
                    Long productId = exchange.getIn().getHeader("productId", Long.class);
                    int quantity = exchange.getIn().getHeader("quantity", Integer.class);
                    boolean success = boutiqueService.purchaseProduct(productId, quantity);
                    exchange.getMessage().setBody(success ? "Achat réussi !" : "Achat échoué.");
                });
    }
}
