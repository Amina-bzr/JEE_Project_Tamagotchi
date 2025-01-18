package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Product;
import fr.pantheonsorbonne.ufr27.miage.service.BoutiqueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class ProductGateway {
    @Inject
    BoutiqueService boutiqueService;

    @Inject
    CamelContext camelContext;

    public ProductDTO purchase(ProductDTO product) { //purchase the product
        System.out.println("BOUTIQUE: inside purchase,...");
        this.boutiqueService.purchaseProduct(product.getId());
        return product;
    }


    public Product getProduct(ProductDTO product) {
        return this.boutiqueService.getProductById(product.getId());
    }

    public List<Product> getAllProducts() {
        return this.boutiqueService.getAllProducts();
    }

    public List<Product> getProductsByCategory(String category) {
        return this.boutiqueService.getProductsByCategory(category);
    }





}
