package fr.pantheonsorbonne.ufr27.miage.exception;

public class AccountForTamagotchiNotFoundException extends RuntimeException {
    public AccountForTamagotchiNotFoundException(Integer tamagotchiId) {
        super("Account not found for Tamagotchi ID: " + tamagotchiId);
    }
}