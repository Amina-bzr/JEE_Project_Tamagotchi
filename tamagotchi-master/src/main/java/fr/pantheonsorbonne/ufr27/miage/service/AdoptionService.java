package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.camel.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.TamagotchiDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OwnerDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.OwnerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.TamagotchiNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Owner;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
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
    TamagotchiDAO tamagotchiDAO;

    @Inject
    OwnerDAO ownerDAO;

    @Inject
    AdoptionGateway adoptionGateway;


    @Transactional
    public Collection<Tamagotchi> getTamagotchisService() {
        return this.tamagotchiDAO.getTamagotchis();

    }

    @Transactional
    public Tamagotchi getTamagotchiService(Integer tamagotchiId) throws TamagotchiNotFoundException {
        Tamagotchi tamagotchi = this.tamagotchiDAO.getTamagotchiById(tamagotchiId);
        if (tamagotchi == null) {
            throw new OwnerNotFoundException("Tamagotchi with ID " + tamagotchiId + " not found.");
        }
        return tamagotchi;
    }

    @Transactional
    public void addTamagotchiService(String name, Integer idOwner) throws OwnerNotFoundException {
        Owner owner = this.ownerDAO.getOwner(idOwner);
        if (owner == null) {
            throw new OwnerNotFoundException("Owner with ID " + idOwner + " not found.");
        }
        Tamagotchi newTamagotchi = new Tamagotchi(owner, name);
        //add compte bancaire
        this.tamagotchiDAO.addTamagotchi(newTamagotchi);

    }
    @Transactional
    public void updateTamagotchiOwner(Integer tamagotchiId, Integer ownerId) throws TamagotchiNotFoundException {
        Tamagotchi tamagotchi = this.tamagotchiDAO.getTamagotchiById(tamagotchiId);
        tamagotchi.setOwner(ownerDAO.getOwner(ownerId));
        this.tamagotchiDAO.updateTamagotchi(tamagotchi);

    }

}