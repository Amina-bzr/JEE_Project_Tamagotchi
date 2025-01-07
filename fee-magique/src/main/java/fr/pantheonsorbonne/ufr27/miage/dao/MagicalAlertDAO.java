package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;

import java.util.Collection;

public interface MagicalAlertDAO {
    void saveAlert(MagicalAlert alert);
    Collection<MagicalAlert> getAllAlerts();
    Collection<MagicalAlert> getAlertsByType(String alertType);

}