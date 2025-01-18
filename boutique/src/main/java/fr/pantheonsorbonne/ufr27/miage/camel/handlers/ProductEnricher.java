package fr.pantheonsorbonne.ufr27.miage.camel.handlers;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Product;
import fr.pantheonsorbonne.ufr27.miage.service.BoutiqueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ProductEnricher {

    @Inject
    BoutiqueService boutiqueService;


    public ProductDTO findProductPrice(ProductDTO product) {
        Product requestedProduct = boutiqueService.getProductById(product.getId());
        product.setPrice(requestedProduct.getPrice());
        System.out.println("Inside findPrice (product was available, send to bank), price is :"+ product.getPrice());
        return product;
    }


    public ProductDTO findProductInfo(ProductDTO product) {
        Product purchasedProduct = boutiqueService.getProductById(product.getId());
        product.setName(purchasedProduct.getName());
        product.setLimitedEdition(purchasedProduct.isLimitedEdition());
        product.setCategory(purchasedProduct.getCategory());
        System.out.println("Inside findProductInfo (could afford, sending all product):"+ product.toString());
        return product;
    }
}
