package fr.pantheonsorbonne.ufr27.miage.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(Integer accountId, double currentBalance, double requestedAmount) {
        super("Insufficient balance in account ID " + accountId + ": current balance is "
                + currentBalance + ", but attempted to withdraw or transfer " + requestedAmount + ".");
    }
}