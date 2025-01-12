package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.MagicalAlert;
import jakarta.transaction.Transactional;

import java.util.Collection;

public interface MagicalAlertDAO {
    @Transactional
    void saveAlert(MagicalAlert alert);
    @Transactional
    Collection<MagicalAlert> getAllAlerts();
    @Transactional
    Collection<MagicalAlert> getAlertsByType(String alertType);
    @Transactional
    void deleteAlert(Integer id);
}