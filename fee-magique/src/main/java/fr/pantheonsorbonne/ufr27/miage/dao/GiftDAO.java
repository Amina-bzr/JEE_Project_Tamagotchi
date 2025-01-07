package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Gift;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class GiftDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createGift(Gift gift) {
        em.persist(gift);
    }

    public List<Gift> findAllGifts() {
        return em.createNamedQuery("Gift.findAll", Gift.class).getResultList();
    }

    public List<Gift> findGiftsByTamagotchi(Integer tamagotchiId) {
        return em.createNamedQuery("Gift.findByTamagotchi", Gift.class)
                .setParameter("tamagotchiId", tamagotchiId)
                .getResultList();
    }

    public List<Gift> findGiftsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return em.createNamedQuery("Gift.findByDateRange", Gift.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}