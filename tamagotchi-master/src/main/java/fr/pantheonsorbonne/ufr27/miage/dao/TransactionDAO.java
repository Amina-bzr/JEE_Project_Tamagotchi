package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.Transaction;

import java.util.Optional;

public interface TransactionDAO {
    void createTransaction(Transaction transaction);
    Optional<Transaction> findTransactionById(Integer transactionId);
}

