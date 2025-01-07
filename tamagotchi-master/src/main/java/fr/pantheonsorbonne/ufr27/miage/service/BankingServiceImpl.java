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

    @Override
    @Transactional
    public Account createAccount(Owner owner, Tamagotchi tamagotchi) {
        Account account = new Account();
        account.setOwner(owner);
        account.setTamagotchi(tamagotchi);
        account.setBalance(100.0); // Default balance
        accountDAO.createAccount(account);
        return account;
    }

    @Override
    public Account getAccount(Owner owner) {
        Optional<Account> account = accountDAO.findAccountByOwner(owner);
        return account.orElseThrow(() -> new IllegalArgumentException("Account not found for owner: " + owner));
    }

    @Override
    @Transactional
    public void deposit(Integer accountId, double amount) {
        Account account = accountDAO.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + accountId));
        account.setBalance(account.getBalance() + amount);

        accountDAO.updateAccount(account);

        // Log the transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transaction.setTimestamp(LocalDateTime.now());
        transactionDAO.createTransaction(transaction);
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, double amount) {
        Account account = accountDAO.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + accountId));

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal");
        }

        account.setBalance(account.getBalance() - amount);
        accountDAO.updateAccount(account);

        // Log the transaction
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
                .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + fromAccountId));
        Account toAccount = accountDAO.findAccountById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + toAccountId));

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for transfer");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);

        // Log the transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(fromAccount);
        transaction.setAmount(-amount);
        transaction.setType("TRANSFER_OUT");
        transaction.setTimestamp(LocalDateTime.now());
        transactionDAO.createTransaction(transaction);

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
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found for id: " + transactionId));
    }
}
