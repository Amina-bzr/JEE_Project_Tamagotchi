package fr.pantheonsorbonne.ufr27.miage.dto;

import java.time.LocalDateTime;

public class AlertDTO {

    private Integer idTamagotchi;

    private AlertTypes alertType;

    private String alertMessage;

    public AlertDTO() {}

    public AlertDTO(Integer idTamagotchi, AlertTypes alertType, String alertMessage) {
        this.idTamagotchi = idTamagotchi;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
    }

    public AlertDTO(Integer idTamagotchi, AlertTypes alertType) {
        this.idTamagotchi = idTamagotchi;
        this.alertType = alertType;
    }

    public Integer getIdTamagotchi() {
        return idTamagotchi;
    }

    public void setIdTamagotchi(Integer idTamagotchi) {
        this.idTamagotchi = idTamagotchi;
    }

    public AlertTypes getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertTypes alertType) {
        this.alertType = alertType;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }
}