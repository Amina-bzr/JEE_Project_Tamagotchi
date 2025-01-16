package fr.pantheonsorbonne.ufr27.miage.model;

public enum ProductCategories {

    VETEMENTS("Vetements"),
    ACCESSOIRES("Accessoires"),
    JOUETS("Jouets");

    private final String value;

    ProductCategories(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
