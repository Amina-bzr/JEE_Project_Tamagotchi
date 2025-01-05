package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransactionDAOImpl implements TransactionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void createTransaction(Transaction transaction) {
        em.persist(transaction);
    }
}
