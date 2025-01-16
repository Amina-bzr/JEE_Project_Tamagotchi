package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TamagotchiDAOImpl implements TamagotchiDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public List<Tamagotchi> getTamagotchis() {
        return em.createQuery("SELECT t FROM Tamagotchi t", Tamagotchi.class).getResultList();
    }

    @Override
    @Transactional
    public List<Tamagotchi> getTamagotchisByOwner(int idOwner) {
        return em.createQuery("SELECT t FROM Tamagotchi t WHERE t.owner.id = :idOwner", Tamagotchi.class)
                .setParameter("idOwner", idOwner)
                .getResultList();
    }

    ;
    @Override
    @Transactional
    public List<Tamagotchi> getTamagotchisWithoutOwner() {
        return em.createQuery("SELECT t FROM Tamagotchi t WHERE t.owner IS NULL", Tamagotchi.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Tamagotchi getTamagotchiById(Integer idTamagotchi) {
        try {
            return em.createQuery("SELECT t FROM Tamagotchi t WHERE t.idTamagotchi = :idTamagotchi", Tamagotchi.class)
                    .setParameter("idTamagotchi", idTamagotchi)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

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

    @Override
    @Transactional
    public Tamagotchi updateTamagotchi(Tamagotchi tamagotchi) {
        Tamagotchi existingTamagotchi = em.find(Tamagotchi.class, tamagotchi.getIdTamagotchi());
        if (existingTamagotchi != null) {
            existingTamagotchi.setName(tamagotchi.getName());
            existingTamagotchi.setHungry(tamagotchi.getHungry());
            existingTamagotchi.setHappiness(tamagotchi.getHappiness());
            existingTamagotchi.setEnergy(tamagotchi.getEnergy());
            existingTamagotchi.setState(tamagotchi.getState());
            return em.merge(existingTamagotchi); // Merge the updated tamagotchi back into the persistence context
        }
        return null; //Tamagotchi not found
    }

    @Override
    @Transactional
    public Tamagotchi addTamagotchi(Tamagotchi tamagotchi) {
        System.out.println("\nINSIDE DAO");
        System.out.println("\nname is " + tamagotchi.getName());
        System.out.println("\nowner is " + tamagotchi.getOwner());
        em.persist(tamagotchi);
        return tamagotchi;
    }
}
