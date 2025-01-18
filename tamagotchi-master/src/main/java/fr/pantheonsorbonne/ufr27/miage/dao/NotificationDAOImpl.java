package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Notification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class NotificationDAOImpl implements NotificationDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public List<Notification> getNotificationsByTamagotchiId(Integer idTamagotchi) {
        return em.createQuery("SELECT n FROM Notification n WHERE n.tamagotchi.idTamagotchi = :idTamagotchi", Notification.class)
                .setParameter("idTamagotchi", idTamagotchi)
                .getResultList();
    }

    @Override
    @Transactional
    public Notification addNotification(Notification notification) {
        em.persist(notification);
        return notification;
    }

    @Override
    @Transactional
    public List<Notification> getUnreadNotifications(Integer idTamagotchi) {
        return em.createQuery("SELECT n FROM Notification n WHERE n.tamagotchi.idTamagotchi = :idTamagotchi AND n.status = 'en attente'", Notification.class)
                .setParameter("idTamagotchi", idTamagotchi)
                .getResultList();
    }

    /**
     * Récupère toutes les notifications liées à un propriétaire.
     * @param ownerId L'identifiant du propriétaire.
     * @return La liste des notifications.
     */
    @Override
    @Transactional
    public List<Notification> getNotificationsByOwner(Integer ownerId) {
        return em.createQuery(
                        "SELECT n FROM Notification n WHERE n.tamagotchi.owner.idOwner = :ownerId",
                        Notification.class
                )
                .setParameter("ownerId", ownerId)
                .getResultList();
    }

    /**
     * Met à jour une notification existante.
     * @param notification La notification à mettre à jour.
     */
    @Override
    @Transactional
    public void updateNotification(Notification notification) {
        em.merge(notification); // Met à jour la notification dans la base de données
    }

}
