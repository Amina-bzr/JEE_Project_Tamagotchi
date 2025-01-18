package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInventory", nullable = false)
    private Integer idInventory;

    @Column(name = "productCategory", nullable = false, length = 45)
    private String productCategory;

    @Column(name = "productName", nullable = false, length = 45)
    private String productName;

    @Column(name = "productId", nullable = false)
    private Integer productId;

    @Column(name = "limitedEdition", nullable = false)
    private Boolean limitedEdition;

    @ManyToOne
    @JoinColumn(name = "tamagotchi", nullable = false)
    public Tamagotchi tamagotchi;


    public Inventory() {
    }

    public Inventory(Integer productId, Tamagotchi tamagotchi, String productCategory, Boolean limitedEdition, String productName) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.limitedEdition = limitedEdition;
        this.tamagotchi = tamagotchi;
    }

    public Boolean getLimitedEdition() {
        return limitedEdition;
    }

    public void setLimitedEdition(Boolean limitedEdition) {
        this.limitedEdition = limitedEdition;
    }

    public Integer getIdInventory() {
        return idInventory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setIdInventory(Integer idInventory) {
        this.idInventory = idInventory;
    }

    public void setTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }


}
