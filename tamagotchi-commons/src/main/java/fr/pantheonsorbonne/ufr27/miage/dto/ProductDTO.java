package fr.pantheonsorbonne.ufr27.miage.dto;

public class ProductDTO {

    private Integer id = null;
    private String name = "";
    private Boolean limitedEdition;
    private String category;
    private Integer tamagotchiId;
    private double price;
    private Integer quantityAvailable;


    public ProductDTO() {};

   //request achat
    public ProductDTO(Integer id, Integer tamagotchiId){
        this.id = id;
        this.tamagotchiId=tamagotchiId;
    }

    //get products
    public ProductDTO(String category, Integer id, String name, Boolean limitedEdition, Integer quantityAvailable, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.limitedEdition = limitedEdition;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    //inventory product
    public ProductDTO(Integer tamagotchiId, String category, Boolean limitedEdition, String name, Integer id) {
        this.tamagotchiId = tamagotchiId;
        this.category = category;
        this.limitedEdition = limitedEdition;
        this.name = name;
        this.id = id;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Integer getTamagotchiId() {
        return tamagotchiId;
    }

    public void setTamagotchiId(Integer tamagotchiId) {
        this.tamagotchiId = tamagotchiId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getLimitedEdition() {
        return limitedEdition;
    }

    public void setLimitedEdition(Boolean limitedEdition) {
        this.limitedEdition = limitedEdition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
