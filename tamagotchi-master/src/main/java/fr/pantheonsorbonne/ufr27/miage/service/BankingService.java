package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.model.Transaction;
import fr.pantheonsorbonne.ufr27.miage.model.Account;


public interface BankingService {

    Account createAccount(Long userId, Long tamagotchiId);

    Account getAccount(Long userId);

    void deposit(Long accountId, double amount);

    void withdraw(Long accountId, double amount);

    void transfer(Long fromAccountId, Long toAccountId, double amount);

    Transaction getTransactionDetails(Long transactionId);
}