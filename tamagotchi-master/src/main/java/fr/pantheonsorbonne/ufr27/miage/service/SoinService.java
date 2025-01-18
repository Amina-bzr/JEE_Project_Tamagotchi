package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Notification;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.model.Treatment;

import java.util.List;

public interface SoinService {

    void checkAndUpdateTamagotchiStates(); // Vérification périodique de l'etat des Tamagotchis

    void sendNotificationForCriticalNeeds(Tamagotchi tamagotchi); // Notif pour besoins critiques

    void sendNotificationForSickness(Tamagotchi tamagotchi); // Notif pour maladie

    void applySickness(Tamagotchi tamagotchi, String sickness); // Appliquer une maladie

    void applyTreatmentToTamagotchi(Integer tamagotchiId, Integer treatmentId); // Appliquer un traitement

    void feedTamagotchi(Integer tamagotchiId, Integer points); // Nourrir un Tamagotchi

    void hydrateTamagotchi(Integer tamagotchiId, Integer points); // Hydrater un Tamagotchi

    void energizeTamagotchi(Integer tamagotchiId, Integer points); // Donner de l'énergie à un Tamagotchi

    void handleTamagotchiDeath(Tamagotchi tamagotchi); // Gestion des décès

    Tamagotchi getTamagotchiById(Integer tamagotchiId); // Récupérer un Tamagotchi par son id

    List <Notification> getNotificationsAndMarkAsRead(Integer ownerId); // marquer comme lu les notifs lues

    //void initializeTreatments(); // Initialise les traitements au démarrage de l'application

    List <Treatment> getTreatmentsForDisease(String disease); // Récupère les traitements pour une maladie
}
