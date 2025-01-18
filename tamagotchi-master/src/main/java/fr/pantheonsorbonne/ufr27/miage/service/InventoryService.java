package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.InventoryDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.ProductAlreadyPurchased;
import fr.pantheonsorbonne.ufr27.miage.exception.TamagotchiNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Inventory;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.util.List;

@ApplicationScoped
public class InventoryService {

    @Inject
    private ProducerTemplate producerTemplate;

    @Inject
    AdoptionService adoptionService;
    @Inject
    InventoryDAO inventoryDAO;


    public List<Inventory> getAllProducts() {
        return inventoryDAO.findAll();
    }


    public List<Inventory> getProductsByCategory(String category) {
        return inventoryDAO.findByCategory(category);
    }

    public Boolean productInInventory(Integer productId, Integer tamagotchiId) {
        try {
            this.inventoryDAO.findByProductAndTamagotchi(productId, tamagotchiId);
            return true;
        } catch (ProductAlreadyPurchased e) {
            return false;
        }
    }


    public void saveToInventory(ProductDTO product) {
        Inventory productInventory = new Inventory();
        productInventory.setProductId(product.getId());
        productInventory.setProductCategory(product.getCategory());
        productInventory.setProductName(product.getName());
        productInventory.setLimitedEdition(product.getLimitedEdition());
        Tamagotchi tamagotchi = new Tamagotchi();
        try {
            tamagotchi = this.adoptionService.getTamagotchiService(product.getTamagotchiId());
        } catch (TamagotchiNotFoundException tamagotchiNotFoundException){
            throw new TamagotchiNotFoundException("Tamagotchi not found!");
        }
        //update happiness points
       tamagotchi.setHappiness(tamagotchi.getHappiness() + 10); //bought a new item => happy tamagotchi!
        this.adoptionService.updateTamagotchi(tamagotchi);
        //save product to tamagotchi's inventory
        productInventory.setTamagotchi(tamagotchi);
        this.inventoryDAO.save(productInventory);
    }
}
