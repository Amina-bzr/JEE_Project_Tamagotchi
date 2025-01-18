package fr.pantheonsorbonne.ufr27.miage.exception;

public class ProductAlreadyPurchased extends RuntimeException {
    public ProductAlreadyPurchased(String message) {
        super(message);
    }
}
