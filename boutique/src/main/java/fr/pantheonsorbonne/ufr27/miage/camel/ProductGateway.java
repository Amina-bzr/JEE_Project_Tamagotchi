package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.service.BoutiqueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class ProductGateway {
    @Inject
    BoutiqueService boutiqueService;

    @Inject
    CamelContext camelContext;

    public void acheter(ProductDTO achat) {
        this.boutiqueService.purchaseProduct(achat.getProductId());
    }
    public void purchaseConfirmation(ProductDTO confirmationAchat ) {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            template.sendBody("direct:confirmationAchat", confirmationAchat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
