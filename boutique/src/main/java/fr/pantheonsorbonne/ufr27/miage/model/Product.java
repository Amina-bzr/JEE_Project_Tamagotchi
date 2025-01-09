package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity // Indique que cette classe est une entité JPA
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id;

    @Column(nullable = false) // Champ non nul
    private String name;

    @Column(nullable = false) // Champ non nul
    private String description;

    @Column(nullable = false) // Champ non nul
    private Double price;

    @Column(nullable = false) // Champ non nul
    private String category;

    @Column(nullable = false) // Champ non nul
    private int quantityAvailable;

    // Constructeur par défaut requis par JPA
    public Product() {}

    // Constructeur personnalisé (optionnel)
    public Product(String name, String description, Double price, String category, int quantityAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantityAvailable = quantityAvailable;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    // Méthode toString (optionnelle, pour le débogage)
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
