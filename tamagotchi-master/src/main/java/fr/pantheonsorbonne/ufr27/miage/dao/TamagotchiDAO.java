package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TamagotchiDAO {

    @Transactional
    List<Tamagotchi> getTamagotchis();

    @Transactional
    List<Tamagotchi> getTamagotchisByOwner(int idOwner);

    @Transactional
    List<Tamagotchi> getTamagotchisWithoutOwner();

    @Transactional
    Tamagotchi getTamagotchiById(Integer idTamagotchi);

    @Transactional
    void deleteTamagotchi(Integer idTamagotchi);

    @Transactional
    Tamagotchi updateTamagotchi(Tamagotchi tamagotchi);

    @Transactional
    Tamagotchi addTamagotchi(Tamagotchi tamagotchi);
}



//tamagotchi
// get tamagotchis : List<Tamagotchi> getTamagotchis()
// get tamagotchis by idOwner : List<Tamagotchi> getTamagotchisByOwner(Integer idOwner)
// get tamagotchis without owners
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