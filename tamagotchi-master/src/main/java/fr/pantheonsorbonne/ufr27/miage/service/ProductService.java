package fr.pantheonsorbonne.ufr27.miage.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.util.Map;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProducerTemplate producerTemplate;

    public String getAllProducts() {
        return producerTemplate.requestBody("direct:getAllProducts", null, String.class);
    }

    public String getProductsByCategory(String category) {
        return producerTemplate.requestBodyAndHeader("direct:getProductsByCategory", null, "category", category, String.class);
    }

    public String purchaseProduct(Long productId, int quantity) {
        return producerTemplate.requestBodyAndHeaders(
                "direct:purchaseProduct",
                null,
                Map.of("productId", productId, "quantity", quantity),
                String.class
        );
    }
}
