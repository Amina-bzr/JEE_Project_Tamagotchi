package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Account> findAccountByNumber(String accountNumber) {
        try {
            Account account = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
            return Optional.of(account);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> findAccountById(Integer accountId) {
        try {
            Account account = em.createQuery("SELECT a FROM Account a WHERE a.id = :accountId", Account.class)
                    .setParameter("accountId", accountId)
                    .getSingleResult();
            return Optional.of(account);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> findAccountByTamagotchiId(Integer tamagotchiId) {
        try {
            Account account = em.createQuery("SELECT a FROM Account a WHERE a.tamagotchi.id = :tamagotchiId", Account.class)
                    .setParameter("tamagotchiId", tamagotchiId)
                    .getSingleResult();
            return Optional.of(account);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void createAccount(Account account) {
        em.persist(account);
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Account account) {
        em.createQuery("DELETE FROM Account a WHERE a.accountNumber = :accountNumber AND a.balance = :balance AND a.tamagotchiId = :tamagotchiId")
                .setParameter("accountNumber", account.getAccountNumber())
                .setParameter("balance", account.getBalance())
                .setParameter("tamagotchiId", account.getTamagotchiId())
                .executeUpdate();
    }

    @Override
    @Transactional
    public Collection<Account> getAllAccounts() {
        return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Override
    @Transactional
    public Account getAccountById(Long accountId) {
        return em.createQuery("SELECT a FROM Account a WHERE a.id = :accountId", Account.class)
                .setParameter("accountId", accountId)
                .getSingleResult();
    }

    @Override
    @Transactional
    public Account getAccountByNumber(String accountNumber) {
        return em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber", accountNumber)
                .getSingleResult();
    }
}