package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Notification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

public class NotificationDAO {

    private EntityManager em;

    public NotificationDAO(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void createNotification(Notification notification) {
        em.persist(notification);
    }

    @Transactional
    public Notification findNotificationById(Integer id) {
        return em.find(Notification.class, id);
    }

    @Transactional
    public void updateNotification(Notification notification) {
        em.merge(notification);
    }

    @Transactional
    public void deleteNotification(Integer id) {
        Notification notification = findNotificationById(id);
        if (notification != null) {
            em.remove(notification);
        }
    }

    @Transactional
    public List<Notification> findAllNotifications() {
        return em.createQuery("SELECT n FROM Notification n", Notification.class).getResultList();
    }
}
