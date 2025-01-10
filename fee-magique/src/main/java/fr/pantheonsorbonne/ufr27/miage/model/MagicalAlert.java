package fr.pantheonsorbonne.ufr27.miage.model;

import java.time.LocalDateTime;

public class MagicalAlert {
    private Integer id;
    private String message;
    private LocalDateTime date;

    public MagicalAlert() {}

    public MagicalAlert(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
