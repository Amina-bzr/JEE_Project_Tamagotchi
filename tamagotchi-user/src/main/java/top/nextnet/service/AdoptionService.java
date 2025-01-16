package top.nextnet.service;
import top.nextnet.camel.gateways.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class AdoptionService {
//    @Inject
//    AdoptionGateway adoptionGateway;
//
//    @Transactional
//    public void createTamagotchi(Integer idOwner, String name ) {
//        TamagotchiDTO newTamagotchi = new TamagotchiDTO(name, idOwner);
//        this.adoptionGateway.createTamagothi(newTamagotchi);
//
//    }
//
//    /*@Transactional
//    public void adoptTamagotchi(Integer idOwner, Integer idTamagotchi ) {
//        Tamagotchi newTamagotchi = new Tamagotchi(name, idOwner);
//        this.adoptionGateway.createTamagothi(newTamagotchi);
//
//    }*/
}