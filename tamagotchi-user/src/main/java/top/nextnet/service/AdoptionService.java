package top.nextnet.service;
import top.nextnet.camel.gateways.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.Collection;



@ApplicationScoped
public class AdoptionService {
    @Inject
    AdoptionGateway adoptionGateway;

    @Transactional
    public void createTamagotchi(Integer idOwner, String name ) {
        Tamagotchi newTamagotchi = new Tamagotchi(name, idOwner);
        this.adoptionGateway.createTamagothi(newTamagotchi);

    }

    /*@Transactional
    public void adoptTamagotchi(Integer idOwner, Integer idTamagotchi ) {
        Tamagotchi newTamagotchi = new Tamagotchi(name, idOwner);
        this.adoptionGateway.createTamagothi(newTamagotchi);

    }*/
}