package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Interaction;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

public class InteractionDAO {

    private EntityManager em;

    public InteractionDAO(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void createInteraction(Interaction interaction) {
        em.persist(interaction);
    }

    @Transactional
    public Interaction findInteractionById(Integer id) {
        return em.find(Interaction.class, id);
    }

    @Transactional
    public void updateInteraction(Interaction interaction) {
        em.merge(interaction);
    }

    @Transactional
    public void deleteInteraction(Integer id) {
        Interaction interaction = findInteractionById(id);
        if (interaction != null) {
            em.remove(interaction);
        }
    }

    @Transactional
    public List<Interaction> findAllInteractions() {
        return em.createQuery("SELECT i FROM Interaction i", Interaction.class).getResultList();
    }
}
