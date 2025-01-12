package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.Account;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface AccountDAO {

    @Transactional
    Optional<Account> findAccountById(Integer accountId);
    @Transactional
    Optional<Account> findAccountByNumber(String accountNumber);
    @Transactional
    Optional<Account> findAccountByTamagotchiId(Integer tamagotchiId);
    @Transactional
    void createAccount(Account account);
    @Transactional
    void updateAccount(Account account);
    @Transactional
    void deleteAccount(Account account);
    @Transactional
    Collection<Account> getAllAccounts();
    @Transactional
    Account getAccountById(Long accountId);
    @Transactional
    Account getAccountByNumber(String accountNumber);
}
