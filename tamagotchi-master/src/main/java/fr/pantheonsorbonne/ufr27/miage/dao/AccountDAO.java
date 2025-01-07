package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountDAO {
    Optional<Account> findAccountByNumber(String accountNumber);
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);

    Collection<Account> getAllAccounts();
    Account getAccountById(Long accountId);
    Account getAccountByNumber(String accountNumber);
}
