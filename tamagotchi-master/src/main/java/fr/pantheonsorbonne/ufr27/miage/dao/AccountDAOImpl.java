package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

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
    public void deleteAccount(String accountNumber) {
        // Chercher l'account avec le numéro fourni
        Optional<Account> accountOpt = findAccountByNumber(accountNumber);

        // Si l'account est présent, on le supprime
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            em.remove(account); // Supprimer le compte
        } else {
            throw new NoResultException("Account with number " + accountNumber + " not found.");
        }
    }



}

