package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.ProductDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped // Permet à Quarkus de gérer le cycle de vie du service
public class BoutiqueService {

    @Inject
    ProductDAO productDAO; // Injection du DAO pour accéder à la base de données

    /**
     * Récupère tous les produits disponibles.
     *
     * @return Une liste de tous les produits.
     */
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    /**
     * Récupère un produit par son ID.
     *
     * @param id L'identifiant du produit.
     * @return Le produit correspondant à l'ID.
     * @throws RuntimeException Si aucun produit n'est trouvé avec cet ID.
     */
    public Product getProductById(Long id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new RuntimeException("Produit non trouvé avec l'ID " + id);
        }
        return product;
    }

    /**
     * Récupère tous les produits d'une catégorie spécifique.
     *
     * @param category La catégorie des produits.
     * @return Une liste des produits correspondant à la catégorie.
     */
    public List<Product> getProductsByCategory(String category) {
        return productDAO.findByCategory(category);
    }

    /**
     * Gère l'achat d'un produit.
     *
     * @param id       L'identifiant du produit à acheter.
     * @param quantity La quantité demandée.
     * @return True si l'achat est réussi, sinon une exception est levée.
     * @throws RuntimeException Si le produit n'existe pas ou si la quantité est insuffisante.
     */
    public boolean purchaseProduct(Long id, int quantity) {
        Product product = getProductById(id);

        // Vérifie la quantité disponible
        if (product.getQuantityAvailable() < quantity) {
            throw new RuntimeException("Quantité insuffisante pour le produit : " + product.getName());
        }

        // Met à jour la quantité disponible
        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
        productDAO.update(product); // Sauvegarde la mise à jour dans la base de données

        return true;
    }
}

