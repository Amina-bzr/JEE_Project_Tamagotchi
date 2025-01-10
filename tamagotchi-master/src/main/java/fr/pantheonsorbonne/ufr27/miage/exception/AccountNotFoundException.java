package fr.pantheonsorbonne.ufr27.miage.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Integer accountId) {
        super("Account with ID " + accountId + " not found.");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}