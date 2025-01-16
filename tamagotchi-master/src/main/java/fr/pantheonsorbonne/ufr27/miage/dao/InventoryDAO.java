package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Inventory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InventoryDAO {

    @PersistenceContext
    EntityManager em;


    /**
     * Récupère tous les objets Inventory.
     *
     * @return Liste complète des objets Inventory.
     */
    @Transactional
    public List<Inventory> findAll() {
        return em.createQuery("SELECT i FROM Inventory i", Inventory.class).getResultList();
    }

    /**
     * Récupère un objet Inventory par son ID.
     *
     * @param idInventory L'ID de l'objet Inventory à récupérer.
     * @return Un Optional contenant l'objet Inventory, ou vide si non trouvé.
     */
    @Transactional
    public Optional<Inventory> findById(Integer idInventory) {
        return Optional.ofNullable(em.find(Inventory.class, idInventory));
    }

    /**
     * Récupère tous les objets Inventory appartenant à un Tamagotchi donné.
     *
     * @param tamagotchiId L'ID du Tamagotchi.
     * @return Liste des objets Inventory associés au Tamagotchi.
     */
    @Transactional
    public List<Inventory> findByTamagotchi(Integer tamagotchiId) {
        return em.createQuery("SELECT i FROM Inventory i WHERE i.tamagotchi.id = :tamagotchiId", Inventory.class)
                .setParameter("tamagotchiId", tamagotchiId)
                .getResultList();
    }

    /**
     * Récupère les objets Inventory par catégorie de produit.
     *
     * @param productCategory La catégorie de produit.
     * @return Liste des objets Inventory correspondant à la catégorie.
     */
    @Transactional
    public List<Inventory> findByCategory(String productCategory) {
        return em.createQuery("SELECT i FROM Inventory i WHERE i.productCategory = :productCategory", Inventory.class)
                .setParameter("productCategory", productCategory)
                .getResultList();
    }

    /**
     * Persiste un objet Inventory dans la base de données.
     *
     * @param inventory L'objet Inventory à persister.
     */
    @Transactional
    public void save(Inventory inventory) {
        em.persist(inventory);
    }

    /**
     * Met à jour un objet Inventory existant dans la base de données.
     *
     * @param inventory L'objet Inventory à mettre à jour.
     */
    @Transactional
    public void update(Inventory inventory) {
        em.merge(inventory);
    }

    /**
     * Supprime un objet Inventory par son ID.
     *
     * @param idInventory L'ID de l'objet Inventory à supprimer.
     */
    @Transactional
    public void delete(Integer idInventory) {
        Inventory inventory = em.find(Inventory.class, idInventory);
        if (inventory != null) {
            em.remove(inventory);
        }
    }
}