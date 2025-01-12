package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TransactionDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Account;
import fr.pantheonsorbonne.ufr27.miage.model.Owner;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.model.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@ApplicationScoped
public class BankingServiceImpl implements BankingService {

    @Inject
    AccountDAO accountDAO;

    @Inject
    TransactionDAO transactionDAO;

    @Inject
    AdoptionService adoptionService;

    @Override
    @Transactional
    public Account createAccount(Integer tamagotchiId) {
        Account account = new Account();
        account.setTamagotchiId(tamagotchiId);
        account.setBalance(100.0); // Default balance
        account.setAccountNumber(java.util.UUID.randomUUID().toString()); //generate a unique account number
        accountDAO.createAccount(account);
        return account;
    }

    @Override
    public Account getAccountByTamagotchi(Integer tamagotchiId) {
        return accountDAO.findAccountByTamagotchiId(tamagotchiId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for Tamagotchi ID: " + tamagotchiId));
    }

    @Override
    public void deposit(Integer accountId, double amount) {
        Account account = accountDAO.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));
        account.setBalance(account.getBalance() + amount);
        accountDAO.updateAccount(account);

        //log the transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transaction.setTimestamp(LocalDateTime.now());
        transactionDAO.createTransaction(transaction);
        //update happiness points
        Tamagotchi tamagotchi = adoptionService.getTamagotchiService(account.getTamagotchiId());
        tamagotchi.setHappiness(tamagotchi.getHappiness() + 5); //new money => happy tamagotchi!
        System.out.println("Received money, so happy!");
        adoptionService.updateTamagotchi(tamagotchi);
        System.out.println("Deposited " + amount+5 + " to Tamagotchi" + tamagotchi.getIdTamagotchi() + tamagotchi.getName());
    }

    @Override
    @Transactional
    public void withdraw(Integer accountId, double amount) {
        Account account = accountDAO.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal");
        }

        account.setBalance(account.getBalance() - amount);
        accountDAO.updateAccount(account);

        //log the transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(-amount);
        transaction.setType("WITHDRAW");
        transaction.setTimestamp(LocalDateTime.now());
        transactionDAO.createTransaction(transaction);
    }

    @Override
    @Transactional
    public void transfer(Integer fromAccountId, Integer toAccountId, double amount) {
        Account fromAccount = accountDAO.findAccountById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + fromAccountId));
        Account toAccount = accountDAO.findAccountById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + toAccountId));

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for transfer");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);

        //log the transaction for sender
        Transaction transactionOut = new Transaction();
        transactionOut.setAccount(fromAccount);
        transactionOut.setAmount(-amount);
        transactionOut.setType("TRANSFER_OUT");
        transactionOut.setTimestamp(LocalDateTime.now());
        transactionDAO.createTransaction(transactionOut);

        //log the transaction for receiver
        Transaction transactionIn = new Transaction();
        transactionIn.setAccount(toAccount);
        transactionIn.setAmount(amount);
        transactionIn.setType("TRANSFER_IN");
        transactionIn.setTimestamp(LocalDateTime.now());
        transactionDAO.createTransaction(transactionIn);
    }

    @Override
    public Transaction getTransactionDetails(Integer transactionId) {
        return transactionDAO.findTransactionById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found for ID: " + transactionId));
    }
}