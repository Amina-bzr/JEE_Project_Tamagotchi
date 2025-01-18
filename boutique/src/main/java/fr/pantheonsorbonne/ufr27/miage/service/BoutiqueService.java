package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.ProductDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.lang.String;
import java.util.List;

@ApplicationScoped
public class BoutiqueService {

    @Inject
    ProductDAO productDAO;


    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }


    public Product getProductById(Integer id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new RuntimeException("Produit non trouvé avec l'ID " + id);
        }
        return product;
    }


    public List<Product> getProductsByCategory(String category) {
        return productDAO.findByCategory(category);
    }


    public void purchaseProduct(Integer id) {
        Product product = getProductById(id);
        System.out.println("\nQuantity available before update: "+product.getQuantityAvailable());
        System.out.println("\nProduct limited?  "+product.isLimitedEdition());
        if (product.isLimitedEdition()) product.setQuantityAvailable(product.getQuantityAvailable()-1);
        System.out.println("\nQuantity available after update: "+product.getQuantityAvailable());
        productDAO.update(product);
    }
}

