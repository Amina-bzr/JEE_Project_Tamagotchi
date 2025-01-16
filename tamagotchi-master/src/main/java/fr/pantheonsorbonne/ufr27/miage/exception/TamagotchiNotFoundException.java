package fr.pantheonsorbonne.ufr27.miage.exception;

public class TamagotchiNotFoundException extends RuntimeException {
    public TamagotchiNotFoundException(String message) {
        super(message);
    }
}