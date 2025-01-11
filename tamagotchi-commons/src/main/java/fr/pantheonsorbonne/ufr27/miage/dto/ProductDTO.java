package fr.pantheonsorbonne.ufr27.miage.dto;

public class ProductDTO {

    private Integer productId ;
    private Integer tamagotchiId;
    private String nomProduit;
    private String categProduit;


    // envoi de master à boutique ( ACHAT )
    public ProductDTO(Integer tamagotchiId, Integer productId){
        this.productId = productId;
        this.tamagotchiId = tamagotchiId;
        this.nomProduit = null;
        this.categProduit = null;

    }

    public Integer getTamagotchiId() {
        return tamagotchiId;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    // envoi de boutique à master ( Confirmation ACHAT => Ajout dans inventaire )
    public ProductDTO(Integer tamagotchiId, Integer productId, String nomProduit, String categProduit) {

        this.productId = productId;
        this.tamagotchiId = tamagotchiId;
        this.nomProduit = nomProduit;
        this.categProduit = categProduit;
    }

    public String getCategProduit() {
        return categProduit;
    }
}
