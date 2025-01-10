package fr.pantheonsorbonne.ufr27.miage.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Integer transactionId) {
        super("Transaction with ID " + transactionId + " not found.");
    }
}