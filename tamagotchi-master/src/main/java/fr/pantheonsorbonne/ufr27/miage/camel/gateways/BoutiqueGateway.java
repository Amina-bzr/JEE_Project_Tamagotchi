package fr.pantheonsorbonne.ufr27.miage.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import fr.pantheonsorbonne.ufr27.miage.service.InventoryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class BoutiqueGateway {

    @Inject
    CamelContext camelContext;

    @Inject
    InventoryService productService;

    @Inject
    BankingService bankingService;


    public List<ProductDTO> getProducts(String category) {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            System.out.println("Getting products from category " + category);
            List<ProductDTO> products = template.requestBodyAndHeader("direct:getProductsFromBoutique", null, "category", category, List.class );
            return products;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des produits : " + e.getMessage(), e);
        }
    }

    public String purchaseProduct(ProductDTO achat) {
        try (ProducerTemplate template = camelContext.createProducerTemplate()) {
            return template.requestBody("direct:achat", achat, String.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    public void withdrawPrice(ProductDTO product) {
//        try {
//            Account account = bankingService.getAccountByTamagotchi(product.getTamagotchiId());
//            this.bankingService.withdraw(account.getId(), product.getPrice());
//        } catch
//    }

    public void saveToInventory (ProductDTO product) {
        System.out.println("Saving product " + product.getName()+" to inventory.");
        this.productService.saveToInventory(product);
    }

}
