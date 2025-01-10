package fr.pantheonsorbonne.ufr27.miage.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super("Invalid input: " + message);
    }
}