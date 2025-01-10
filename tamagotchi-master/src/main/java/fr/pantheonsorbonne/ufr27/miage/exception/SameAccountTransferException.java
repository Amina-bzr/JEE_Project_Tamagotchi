package fr.pantheonsorbonne.ufr27.miage.exception;

public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException(Integer accountId) {
        super("Cannot transfer money to the same account. Account ID: " + accountId);
    }
}