package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Discussion;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

public class DiscussionDAO {

    private EntityManager em;

    public DiscussionDAO(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void createDiscussion(Discussion discussion) {
        em.persist(discussion);
    }

    @Transactional
    public Discussion findDiscussionById(Integer id) {
        return em.find(Discussion.class, id);
    }

    @Transactional
    public void updateDiscussion(Discussion discussion) {
        em.merge(discussion);
    }

    @Transactional
    public void deleteDiscussion(Integer id) {
        Discussion discussion = findDiscussionById(id);
        if (discussion != null) {
            em.remove(discussion);
        }
    }

    @Transactional
    public List<Discussion> findAllDiscussions() {
        return em.createQuery("SELECT d FROM Discussion d", Discussion.class).getResultList();
    }
}
