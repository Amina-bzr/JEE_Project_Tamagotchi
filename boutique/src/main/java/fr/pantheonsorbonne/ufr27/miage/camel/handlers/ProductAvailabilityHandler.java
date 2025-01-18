package fr.pantheonsorbonne.ufr27.miage.camel.handlers;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Product;
import fr.pantheonsorbonne.ufr27.miage.service.BoutiqueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class ProductAvailabilityHandler {

    @Inject
    BoutiqueService boutiqueService;

    @Handler
    public void checkProductAvailability(Exchange exchange) {
        ProductDTO productRequested = exchange.getMessage().getBody(ProductDTO.class);
        Product product = boutiqueService.getProductById(productRequested.getId());
        System.out.println("Checking availability for product : "+product.getName());
        if (product.isLimitedEdition() && product.getQuantityAvailable()==0) {
            System.out.println("Product unavailable : "+product.getQuantityAvailable());
            exchange.getMessage().
                    setHeader("available", false);
        } else {
            exchange.getMessage().setHeader("available", true);
            System.out.println("\nProduct available, quantity : "+product.getQuantityAvailable());
   }
    }

}
