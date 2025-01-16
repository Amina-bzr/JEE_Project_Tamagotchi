package fr.pantheonsorbonne.ufr27.miage.dto;

public class ProductDTO {

    private Integer id = null;
    private String name = "";
    private String category = "";
    private Double price = 0.0;
    private int quantityAvailable = 0;
    private Integer tamagotchiId = null;



    public ProductDTO() {};

    // envoi de master à boutique ( ACHAT )
    public ProductDTO(Integer tamagotchiId, Integer id){
        this.id = id;
        this.tamagotchiId = tamagotchiId;
    }

    // envoi de master à user
    public ProductDTO(int quantityAvailable, Double price, String category, String name, Integer id) {
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.category = category;
        this.name = name;
        this.id = id;
    }


    // envoi de boutique à master ( Confirmation ACHAT => Ajout dans inventaire )
    public ProductDTO(Integer tamagotchiId, Integer id, String name, String category) {

        this.id = id;
        this.tamagotchiId = tamagotchiId;
        this.name = name;
        this.category = category;
    }

    public Integer getTamagotchiId() {
        return tamagotchiId;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setTamagotchiId(Integer tamagotchiId) {
        this.tamagotchiId = tamagotchiId;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
