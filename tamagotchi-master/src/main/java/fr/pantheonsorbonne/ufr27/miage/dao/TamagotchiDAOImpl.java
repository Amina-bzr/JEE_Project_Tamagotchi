package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TamagotchiDAOImpl implements TamagotchiDAO {

    @Inject
    EntityManager em;

    // Get all Tamagotchis
    @Override
    @Transactional
    public List<Tamagotchi> getTamagotchis() {
        return em.createQuery("SELECT t FROM Tamagotchi t", Tamagotchi.class).getResultList();
    }

    // Get all Tamagotchis by idOwner
    @Override
    @Transactional
    public List<Tamagotchi> getTamagotchisByOwner(int idOwner) {
        return em.createQuery("SELECT t FROM Tamagotchi t WHERE t.owner.id = :idOwner", Tamagotchi.class)
                .setParameter("idOwner", idOwner)
                .getResultList();
    }

    ;
    // Get all Tamagotchis without owner
    @Override
    @Transactional
    public List<Tamagotchi> getTamagotchisWithoutOwner() {
        return em.createQuery("SELECT t FROM Tamagotchi t WHERE t.owner IS NULL", Tamagotchi.class)
                .getResultList();
    }

    // Get a specific Tamagotchi by idTamagotchi
    @Override
    @Transactional
    public Tamagotchi getTamagotchiById(Integer idTamagotchi) {
        try {
            return em.createQuery("SELECT t FROM Tamagotchi t WHERE t.idTamagotchi = :idTamagotchi", Tamagotchi.class)
                    .setParameter("idTamagotchi", idTamagotchi)
                    .getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            return null; // No result found or multiple results found
        }
    }

    // Delete a Tamagotchi by idTamagotchi
    @Override
    @Transactional
    public void deleteTamagotchi(Integer idTamagotchi) {
        Tamagotchi tamagotchiToDelete = getTamagotchiById(idTamagotchi);
        if (tamagotchiToDelete != null) {
            em.remove(tamagotchiToDelete);
        } else {
            System.out.println("Le Tamagotchi avec l'ID " + idTamagotchi + " n'existe pas.");
        }
    }

    // Update a Tamagotchi's details
    @Override
    @Transactional
    public Tamagotchi updateTamagotchi(Tamagotchi tamagotchi) {
        Tamagotchi existingTamagotchi = em.find(Tamagotchi.class, tamagotchi.getIdTamagotchi());
        if (existingTamagotchi != null) {
            existingTamagotchi.setName(tamagotchi.getName());
            existingTamagotchi.setHungry(tamagotchi.getHungry());
            existingTamagotchi.setHappiness(tamagotchi.getHappiness());
            existingTamagotchi.setEnergy(tamagotchi.getEnergy());
            existingTamagotchi.setHealth(tamagotchi.getHealth());
            existingTamagotchi.setState(tamagotchi.getState());
            return em.merge(existingTamagotchi); // Merge the updated tamagotchi back into the persistence context
        }
        return null; // Tamagotchi not found
    }

    // Add a new Tamagotchi
    @Override
    @Transactional
    public Tamagotchi addTamagotchi(Tamagotchi tamagotchi) {
        em.persist(tamagotchi);
        return tamagotchi; // Return the persisted Tamagotchi
    }
}
