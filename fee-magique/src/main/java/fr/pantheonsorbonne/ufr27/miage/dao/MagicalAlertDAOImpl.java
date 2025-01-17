package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class MagicalAlertDAOImpl implements MagicalAlertDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveAlert(MagicalAlert alert) {
        em.persist(alert);
    }

    @Override
    @Transactional
    public Collection<MagicalAlert> getAllAlerts() {
        return em.createQuery("SELECT a FROM MagicalAlert a", MagicalAlert.class).getResultList();
    }

    @Override
    @Transactional
    public Collection<MagicalAlert> getAlertsByType(String alertType) {
        return em.createQuery("SELECT a FROM MagicalAlert a WHERE a.alertType = :type", MagicalAlert.class)
                .setParameter("type", alertType)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteAlert(Integer id) {
        MagicalAlert alert = em.find(MagicalAlert.class, id);
        if (alert != null) {
            em.remove(alert);
        }
    }
}
