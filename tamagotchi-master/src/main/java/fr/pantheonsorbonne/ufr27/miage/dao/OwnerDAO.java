package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.OwnerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Owner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OwnerDAO {

    @Transactional
    Owner addOwner(Owner owner);

    @Transactional
    Owner getOwner(Integer idOwner);

    @Transactional
    List<Owner> getOwners();

    @Transactional
    void deleteOwner(Integer idOwner);

    @Transactional
    Owner updateOwner(Owner owner);
}




//tamagotchi
// get tamagotchis : List<Tamagotchi> getTamagotchis()
// get tamagotchis by idOwner : List<Tamagotchi> getTamagotchisByOwner(Integer idOwner)
// get tamagotchi by idTamagotchi : List<Tamagotchi> getTamagotchisByOwner(Integer idTamagotchi)
// delete tamagotchi : void deleteTamagotchi(Integer idTamagotchi)
// update tamagotchi : Tamagotchi updateTamagotchi(Tamagotchi tamagotchi)
// add tamagotchi : Tamagotchi addTamagotchi(Tamagotchi tamagotchi)


//Owner
// create owner : Owner addOwner(Owner owner)
// get owner by idOwner : Owner getOwner(Integer idOwner)
// get all owners : List<Owner> getOwners()
// delete Owner : void deleteOwner(Integer idOwner)
// update Owner :  Tamagotchi updateOwner(Owner tamagotchi)