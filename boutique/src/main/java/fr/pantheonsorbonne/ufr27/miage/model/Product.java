package fr.pantheonsorbonne.ufr27.miage.model;

import com.sun.tools.xjc.model.CDefaultValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private Boolean limitedEdition; //if limited edition quantity is specified

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = true)
    private Integer quantityAvailable;

    public Product() {}

    public Product(String name, Boolean limitedEdition, Double price, String category, Integer quantityAvailable) {
        this.name = name;
        this.limitedEdition = limitedEdition;
        this.price = price;
        this.category = category;
        this.quantityAvailable = quantityAvailable;
    }

    public Boolean isLimitedEdition() {
        return limitedEdition;
    }

    public void setLimitedEdition(Boolean limitedEdition) {
        this.limitedEdition = limitedEdition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limitedEdition='" + limitedEdition + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
