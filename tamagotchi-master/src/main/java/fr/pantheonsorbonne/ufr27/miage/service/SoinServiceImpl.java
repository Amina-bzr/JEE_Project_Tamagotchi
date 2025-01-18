package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SoinGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.NotificationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TamagotchiDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TreatmentDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertTypes;
import fr.pantheonsorbonne.ufr27.miage.exception.*;
import fr.pantheonsorbonne.ufr27.miage.model.Notification;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.model.Treatment;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class SoinServiceImpl implements SoinService {

    @Inject
    TamagotchiDAO tamagotchiDAO;

    @Inject
    NotificationDAO notificationDAO;

    @Inject
    TreatmentDAO treatmentDAO;

    @Inject
    SoinGateway soinGateway;

    @Inject
    BankingService bankingService;

    @Scheduled(every = "70s") // Exécution toutes les 30 secondes de la verification de l'etat des tamagotchi
    void scheduledCheckAndUpdateTamagotchiStates() {
        System.out.println("On a verifie l'etat des tamagotchis !");
        checkAndUpdateTamagotchiStates();
    }

    @Override
    public List<Treatment> getTreatmentsForDisease(String disease) {
        List<Treatment> treatments = treatmentDAO.getTreatmentsByDisease(disease);
        if (treatments.isEmpty()) {
            throw new TreatmentNotFoundException("Aucun traitement disponible pour la maladie : " + disease);
        }
        return treatments;
    }

    @Override
    public void checkAndUpdateTamagotchiStates() {
        List<Tamagotchi> tamagotchis = tamagotchiDAO.getTamagotchis();
        System.out.println("Exécution de checkAndUpdateTamagotchiStates à : " + LocalDateTime.now());
        if(tamagotchis != null) {
            for (Tamagotchi tamagotchi : tamagotchis) {
                LocalDateTime now = LocalDateTime.now();
                if (tamagotchi.getLastUpdateTime().plusSeconds(70).isBefore(now)) {
                    degradeTamagotchiAttributes(tamagotchi);
                    tamagotchi.setLastUpdateTime(now);
                    tamagotchiDAO.updateTamagotchi(tamagotchi);
                }
                checkCriticalNeeds(tamagotchi);
            }
        }
    }


    private void degradeTamagotchiAttributes(Tamagotchi tamagotchi) {
        int degradation = 10;
        if(!"dead".equals(tamagotchi.getState())) { // Si le tamagotchi est mort on ne le degrade plus
            if (!"obesity".equals(tamagotchi.getDisease())) {
                tamagotchi.setHungry(tamagotchi.getHungry() - degradation);
                System.out.println("Hungry =: " + tamagotchi.getHungry());
                tamagotchi.setThirst(tamagotchi.getThirst() - degradation);
                System.out.println(", Thirst =: " + tamagotchi.getThirst());
                tamagotchi.setEnergy(tamagotchi.getEnergy() - degradation);
                System.out.println(", Energy =: " + tamagotchi.getEnergy());
                tamagotchi.setHappiness(tamagotchi.getHappiness() - degradation);
                System.out.println(", Happiness =: " + tamagotchi.getHappiness());

            } else {
                tamagotchi.setHungry(tamagotchi.getHungry() + degradation);
                tamagotchi.setThirst(tamagotchi.getThirst() + degradation);
                tamagotchi.setEnergy(tamagotchi.getEnergy() + degradation);
            }
        }

    }

    private void checkCriticalNeeds(Tamagotchi tamagotchi) {
        if (tamagotchi.getHungry().equals(tamagotchi.getThresholdMin()) ||
                tamagotchi.getThirst().equals(tamagotchi.getThresholdMin()) ||
                tamagotchi.getEnergy().equals(tamagotchi.getThresholdMin())) {
            sendNotificationForCriticalNeeds(tamagotchi);
        }

        if (tamagotchi.getHungry() < tamagotchi.getThresholdMin() ||
                tamagotchi.getThirst() < tamagotchi.getThresholdMin() ||
                tamagotchi.getEnergy() < tamagotchi.getThresholdMin()) {
            applySickness(tamagotchi, "malnutrition");
            sendNotificationForSickness(tamagotchi);
            System.out.println("je suis malade de : "+tamagotchi.getDisease());
        }

        if (tamagotchi.getHungry().equals(tamagotchi.getThresholdMax()) ||
                tamagotchi.getThirst().equals(tamagotchi.getThresholdMax()) ||
                tamagotchi.getEnergy().equals(tamagotchi.getThresholdMax())) {
            applySickness(tamagotchi, "obesity");
            sendNotificationForSickness(tamagotchi);
            System.out.println("je suis malade de : "+tamagotchi.getDisease());

        }

        if (tamagotchi.getHungry() <= 0 || tamagotchi.getThirst() <= 0 ||
                tamagotchi.getEnergy() <= 0 || tamagotchi.getHappiness() <= 0 ||
                tamagotchi.getHungry() > tamagotchi.getThresholdMax() ||
                tamagotchi.getThirst() > tamagotchi.getThresholdMax() ||
                tamagotchi.getEnergy() > tamagotchi.getThresholdMax()) {
            handleTamagotchiDeath(tamagotchi);
        }
    }

    @Override
    public void sendNotificationForCriticalNeeds(Tamagotchi tamagotchi) {
        try {
            Notification notification = new Notification();
            notification.setTamagotchi(tamagotchi);
            notification.setType("besoin");
            notification.setContent("Votre Tamagotchi a des besoins critiques !");
            notification.setStatus("en attente");
            notification.setCreationTime(LocalDateTime.now());
            notificationDAO.addNotification(notification);
        }catch (Exception e){
            throw new NotificationCreationException("Erreur lors de la création de la notification de besoin.");
        }
    }

    @Override
    public void handleTamagotchiDeath(Tamagotchi tamagotchi) {
        if (tamagotchi == null) {
            throw new TamagotchiNotFoundException("Tamagotchi introuvable : pas de declaration de deces possible.");
        }
        Notification deathNotification = new Notification();
        deathNotification.setTamagotchi(tamagotchi);
        deathNotification.setType("décès");
        deathNotification.setContent("Votre Tamagotchi est mort !");
        deathNotification.setStatus("en attente");
        deathNotification.setCreationTime(LocalDateTime.now());
        notificationDAO.addNotification(deathNotification);
        tamagotchi.setState("dead");
        tamagotchiDAO.updateTamagotchi(tamagotchi);
        System.out.println("je suis : "+tamagotchi.getState());
        soinGateway.notifyFairy(new AlertDTO(tamagotchi.getIdTamagotchi(),AlertTypes.NEGLECT,deathNotification.getContent()));
    }

    @Override
    public void applySickness(Tamagotchi tamagotchi, String sickness) {
        if (tamagotchi == null) {
            throw new InvalidStateException("Le Tamagotchi est nul.");
        }
        tamagotchi.setDisease(sickness);
        tamagotchi.setState("sick");
        tamagotchiDAO.updateTamagotchi(tamagotchi);
        System.out.println("La maladie"+sickness+" est applique !!!");
    }

    @Override
    public void applyTreatmentToTamagotchi(Integer tamagotchiId, Integer treatmentId) {
        Tamagotchi tamagotchi = tamagotchiDAO.getTamagotchiById(tamagotchiId);
        Treatment treatment = treatmentDAO.getTreatmentById(treatmentId);
        if (treatment == null) {
            throw new TreatmentNotFoundException("Traitement avec l'ID " + treatmentId + " introuvable.");
        }
        if (tamagotchi == null) {
            throw new TamagotchiNotFoundException("Tamagocthi avec l'ID " + tamagotchiId + " introuvable.");
        }
        if(tamagotchi.getDisease()==null){
            throw new TamagotchiNotSickException("Votre Tamagotchi n'a pas besoin de traitement : il n'est pas malade !");
        }
        if ("malnutrition".equals(tamagotchi.getDisease())) {
            // On verifie si il y a assez d'argent dans le compte
            bankingService.withdraw(bankingService.getAccountByTamagotchi(tamagotchiId).getId(),treatment.getCost());

            // Si il y a assez d'argent on applique le traitement
            tamagotchi.setHungry(Math.min(tamagotchi.getHungry() + treatment.getEffect(), tamagotchi.getThresholdMax()));
            tamagotchi.setEnergy(Math.min(tamagotchi.getEnergy() + treatment.getEffect(), tamagotchi.getThresholdMax()));
            tamagotchi.setThirst(Math.min(tamagotchi.getThirst() + treatment.getEffect(), tamagotchi.getThresholdMax()));
        } else if ("obesity".equals(tamagotchi.getDisease())) {
            tamagotchi.setHungry(Math.max(tamagotchi.getHungry() + treatment.getEffect(), tamagotchi.getThresholdMin()));
            tamagotchi.setEnergy(Math.max(tamagotchi.getEnergy() + treatment.getEffect(), tamagotchi.getThresholdMin()));
            tamagotchi.setThirst(Math.max(tamagotchi.getThirst() + treatment.getEffect(), tamagotchi.getThresholdMin()));
        }

        if ((tamagotchi.getHungry() >= tamagotchi.getThresholdMin() && tamagotchi.getHungry() <= tamagotchi.getThresholdMax())
                && (tamagotchi.getEnergy() >= tamagotchi.getThresholdMin() && tamagotchi.getEnergy() <= tamagotchi.getThresholdMax())
                && (tamagotchi.getThirst() >= tamagotchi.getThresholdMin() && tamagotchi.getThirst() <= tamagotchi.getThresholdMax()))
        {
            tamagotchi.setDisease(null);
        }

        tamagotchiDAO.updateTamagotchi(tamagotchi);
    }

    @Override
    public void feedTamagotchi(Integer tamagotchiId, Integer points) {
        System.out.println("ID EST : "+tamagotchiId);
        Tamagotchi tamagotchi = tamagotchiDAO.getTamagotchiById(tamagotchiId);
        System.out.println("ID EST : "+tamagotchiId);
        System.out.println("NAME EST : "+tamagotchi.getName());
        System.out.println("STATE EST : "+tamagotchi.getState());
        if("dead".equals(tamagotchi.getState())){
            throw new DeadTamagotchiException("Impossible de nourrir un Tamagocthi decede");
        }
        tamagotchi.setHungry(Math.min(tamagotchi.getHungry() + points, tamagotchi.getThresholdMax()));
        //Quand on nourrit le tamagotchi son bonheur augmente
        tamagotchi.setHappiness(Math.min(tamagotchi.getHungry() + points, tamagotchi.getThresholdMax()));
        tamagotchiDAO.updateTamagotchi(tamagotchi);
    }

    @Override
    public void hydrateTamagotchi(Integer tamagotchiId, Integer points) {
        //System.out.println("HYDRATE : "+tamagotchiId);
        //System.out.println("C'EST LUI : "+tamagotchi);
        Tamagotchi tamagotchi = tamagotchiDAO.getTamagotchiById(tamagotchiId);
        if("dead".equals(tamagotchi.getState())){
            throw new DeadTamagotchiException("Impossible d'hydrater un Tamagocthi decede");
        }
        tamagotchi.setThirst(Math.min(tamagotchi.getThirst() + points, tamagotchi.getThresholdMax()));
        //Quand on hydrate le tamagotchi son bonheur augmente
        tamagotchi.setHappiness(Math.min(tamagotchi.getHungry() + points, tamagotchi.getThresholdMax()));
        tamagotchiDAO.updateTamagotchi(tamagotchi);
    }

    @Override
    public void energizeTamagotchi(Integer tamagotchiId, Integer points) {
        //System.out.println("ENERGIZE : "+tamagotchiId);
        //System.out.println("C'EST LUI : "+tamagotchi);
        Tamagotchi tamagotchi = tamagotchiDAO.getTamagotchiById(tamagotchiId);
        if("dead".equals(tamagotchi.getState())){
            throw new DeadTamagotchiException("Impossible d'energiser un Tamagocthi decede");
        }
        tamagotchi.setEnergy(Math.min(tamagotchi.getEnergy() + points, tamagotchi.getThresholdMax()));
        tamagotchiDAO.updateTamagotchi(tamagotchi);
    }


    @Override
    public void sendNotificationForSickness(Tamagotchi tamagotchi) {
        Notification sickNotification = new Notification();
        sickNotification.setTamagotchi(tamagotchi);
        sickNotification.setType("maladie");
        sickNotification.setContent("Votre Tamagotchi est malade de : "+tamagotchi.getDisease());
        sickNotification.setStatus("en attente");
        sickNotification.setCreationTime(LocalDateTime.now());
        notificationDAO.addNotification(sickNotification);
    }

    @Override
    public Tamagotchi getTamagotchiById(Integer tamagotchiId) {
        if(tamagotchiDAO.getTamagotchiById(tamagotchiId)==null){
            throw new TamagotchiNotFoundException("Tamagotchi inexistant");
        }
        return tamagotchiDAO.getTamagotchiById(tamagotchiId);
    }

    @Override
    public List<Notification> getNotificationsAndMarkAsRead(Integer ownerId) {
        List<Notification> notifications = notificationDAO.getNotificationsByOwner(ownerId);
        if(notifications!= null) {
            for (Notification notification : notifications) {
                if ("en attente".equals(notification.getStatus())) {
                    notification.setStatus("lu");
                    notificationDAO.updateNotification(notification);
                }
            }
        }
        return notifications;
    }
}
