package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;


    @Column(nullable = false)
    private Integer tamagotchiId; // ID du Tamagotchi associé (lié au DTO)



    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getTamagotchiId() {
        return tamagotchiId;
    }

    public void setTamagotchiId(Integer tamagotchiId) {
        this.tamagotchiId = tamagotchiId;
    }



    // Default constructor
    public Account() {
    }

    // Constructor
    public Account(String accountNumber, Integer tamagotchiId) {
        this.accountNumber = accountNumber;
        this.tamagotchiId = tamagotchiId;
        this.balance = 100.0; // Default balance is 100 Gotchi d'or
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
    }

}