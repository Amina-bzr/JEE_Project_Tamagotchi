package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.InventoryDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.TamagotchiNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Inventory;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.util.Map;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProducerTemplate producerTemplate;

    @Inject
    AdoptionService adoptionService;
    @Inject
    InventoryDAO inventoryDAO;

    /**
     * Récupère tous les produits depuis le microservice Boutique.
     *
     * @return La liste de tous les produits en format JSON (réponse brute de la route).
     */
    public String getAllProducts() {
        return producerTemplate.requestBody("direct:getAllProductsFromBoutique", null, String.class);
    }

    /**
     * Récupère les produits d'une catégorie spécifique depuis le microservice Boutique.
     *
     * @param category La catégorie des produits à rechercher.
     * @return Les produits correspondant à la catégorie en format JSON.
     */
    public String getProductsByCategory(String category) {
        return producerTemplate.requestBodyAndHeader(
                "direct:getProductsByCategoryFromBoutique",
                null,
                "category",
                category,
                String.class
        );
    }

    /**
     * Effectue un achat d'un produit via le microservice Boutique.
     *
     * @param productId L'ID du produit à acheter.
     * @param quantity  La quantité à acheter.
     * @return La réponse du microservice Boutique concernant l'achat.
     */
    public String purchaseProduct(Long productId, int quantity) {
        return producerTemplate.requestBodyAndHeaders(
                "direct:purchaseProductFromBoutique",
                null,
                Map.of("productId", productId, "quantity", quantity),
                String.class
        );
    }

    public void saveToInventory(ProductDTO product) {
        Inventory productInventory = new Inventory();
        productInventory.setProductId(product.getProductId());
        productInventory.setProductCategory(product.getCategProduit());
        productInventory.setProductName(product.getNomProduit());
        Tamagotchi tamagotchi = new Tamagotchi();
        try {
            tamagotchi = this.adoptionService.getTamagotchiService(product.getTamagotchiId());
        } catch (TamagotchiNotFoundException tamagotchiNotFoundException){
            throw new TamagotchiNotFoundException("le tamagotchi n'existe pas !");

        }
        productInventory.setTamagotchi(tamagotchi);
        this.inventoryDAO.save(productInventory);

    }
}
