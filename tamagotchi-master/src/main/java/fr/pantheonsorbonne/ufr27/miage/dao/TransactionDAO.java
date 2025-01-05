package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.Transaction;

public interface TransactionDAO {
    void createTransaction(Transaction transaction);
}

