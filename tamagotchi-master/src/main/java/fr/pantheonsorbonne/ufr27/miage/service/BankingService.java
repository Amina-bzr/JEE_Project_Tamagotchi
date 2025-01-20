package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.model.Transaction;
import fr.pantheonsorbonne.ufr27.miage.model.Account;


public interface BankingService {

    Account createAccount(Integer tamagotchiId);

    Account getAccountByTamagotchi(Integer tamagotchiId);

    void deposit(Integer accountId, double amount);

    void withdraw(Integer accountId, double amount);

    void transfer(Integer fromAccountId, Integer toAccountId, double amount);

    Transaction getTransactionDetails(Integer transactionId);

    void DailyMoney();
}