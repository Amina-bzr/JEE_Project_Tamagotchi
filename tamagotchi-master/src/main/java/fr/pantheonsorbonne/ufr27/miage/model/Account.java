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

    @OneToOne
    @JoinColumn(name = "idTamagotchi", nullable = false)
    private Tamagotchi tamagotchi;

    @ManyToOne
    @JoinColumn(name = "idOwner" , nullable = false)
    private Owner owner; // Association avec un utilisateur

    @Column(nullable = false)
    private LocalDateTime creationDate;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }

    public void setTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    // Default constructor
    public Account(){
    };

    public Account(Integer id,String accountNumber,Tamagotchi tamagotchi,Owner owner) {
        this.balance = 100.0; // Default balance is 100 Gotchi d'or
        this.creationDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
    }


}