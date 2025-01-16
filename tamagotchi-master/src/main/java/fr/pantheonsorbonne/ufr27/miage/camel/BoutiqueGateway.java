package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.service.InventoryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class BoutiqueGateway {

    @Inject
    CamelContext camelContext;

    @Inject
    InventoryService productService;

    public String getAllProducts() {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            return template.requestBody("direct:getAllProductsFromBoutique", null, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de tous les produits : " + e.getMessage(), e);
        }
    }

    public String getProductsByCategory(String category) {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            return template.requestBodyAndHeader("direct:getProductsByCategoryFromBoutique", null, "category", category, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des produits par catégorie : " + e.getMessage(), e);
        }
    }

    public void purchaseProduct(ProductDTO achat ) {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            template.sendBody("direct:achat", achat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveToInventory (ProductDTO product) {
        this.productService.saveToInventory(product);
    }

}