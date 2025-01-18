package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Notification;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NotificationDAO {

    @Transactional
    Notification addNotification(Notification notification);

    @Transactional
    public void updateNotification(Notification notification);

    @Transactional
    List<Notification> getNotificationsByTamagotchiId(Integer idTamagotchi);

    @Transactional
    public List<Notification> getNotificationsByOwner(Integer ownerId);

    @Transactional
    List<Notification> getUnreadNotifications(Integer idTamagotchi);
}
