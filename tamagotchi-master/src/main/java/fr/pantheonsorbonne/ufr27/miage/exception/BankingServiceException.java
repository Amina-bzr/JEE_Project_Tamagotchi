package fr.pantheonsorbonne.ufr27.miage.exception;

public class BankingServiceException extends RuntimeException {
    public BankingServiceException(String message) {
        super("Banking service error: " + message);
    }

    public BankingServiceException(String message, Throwable cause) {
        super("Banking service error: " + message, cause);
    }
}