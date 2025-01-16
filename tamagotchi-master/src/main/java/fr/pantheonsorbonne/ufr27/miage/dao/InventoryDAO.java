package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Inventory;

import fr.pantheonsorbonne.ufr27.miage.model.ProductCategories;
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



    @Transactional
    public List<Inventory> findAll() {
        return em.createQuery("SELECT i FROM Inventory i", Inventory.class).getResultList();
    }


    @Transactional
    public Optional<Inventory> findById(Integer idInventory) {
        return Optional.ofNullable(em.find(Inventory.class, idInventory));
    }


    @Transactional
    public List<Inventory> findByTamagotchi(Integer tamagotchiId) {
        return em.createQuery("SELECT i FROM Inventory i WHERE i.tamagotchi.id = :tamagotchiId", Inventory.class)
                .setParameter("tamagotchiId", tamagotchiId)
                .getResultList();
    }



    @Transactional
    public List<Inventory> findByCategory(String productCategory) {
        return em.createQuery("SELECT i FROM Inventory i WHERE i.productCategory = :productCategory", Inventory.class)
                .setParameter("productCategory", productCategory)
                .getResultList();
    }


    @Transactional
    public void save(Inventory inventory) {
        em.persist(inventory);
    }


    @Transactional
    public void update(Inventory inventory) {
        em.merge(inventory);
    }


    @Transactional
    public void delete(Integer idInventory) {
        Inventory inventory = em.find(Inventory.class, idInventory);
        if (inventory != null) {
            em.remove(inventory);
        }
    }
}
