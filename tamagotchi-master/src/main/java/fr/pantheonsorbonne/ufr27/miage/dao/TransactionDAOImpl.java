package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class TransactionDAOImpl implements TransactionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void createTransaction(Transaction transaction) {
        em.persist(transaction);
    }

    @Override
    public Optional<Transaction> findTransactionById(Integer transactionId) {
        try {
            Transaction transaction = em.createQuery("SELECT t FROM Transaction t WHERE t.id = :transactionId", Transaction.class)
                    .setParameter("transactionId", transactionId)
                    .getSingleResult();
            return Optional.of(transaction);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
