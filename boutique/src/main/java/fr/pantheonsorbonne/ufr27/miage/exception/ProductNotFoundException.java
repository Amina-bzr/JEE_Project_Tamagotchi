package fr.pantheonsorbonne.ufr27.miage.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message); // Transmet le message à la classe parente RuntimeException
    }
}
