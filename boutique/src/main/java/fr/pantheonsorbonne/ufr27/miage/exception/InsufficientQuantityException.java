package fr.pantheonsorbonne.ufr27.miage.exception;

public class InsufficientQuantityException extends RuntimeException {

    public InsufficientQuantityException(String message) {
        super(message); // Transmet le message à la classe parente RuntimeException
    }
}
