package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.camel.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.TamagotchiDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OwnerDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.OwnerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.TamagotchiHasOwner;
import fr.pantheonsorbonne.ufr27.miage.exception.TamagotchiNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Owner;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.resources.AdoptionResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.Collection;


@ApplicationScoped
public class AdoptionService {

    @PersistenceContext
    EntityManager em;

    @Inject
    TamagotchiDAO tamagotchiDAO;

    @Inject
    OwnerDAO ownerDAO;




    public Tamagotchi getTamagotchiService(Integer tamagotchiId) throws TamagotchiNotFoundException {
        Tamagotchi tamagotchi = this.tamagotchiDAO.getTamagotchiById(tamagotchiId);
        if (tamagotchi == null) {
            throw new OwnerNotFoundException("Tamagotchi with ID " + tamagotchiId + " not found.");
        }
        return tamagotchi;
    }

    public Tamagotchi addTamagotchiService(String name, Integer idOwner) throws OwnerNotFoundException {

        Owner owner = this.ownerDAO.getOwner(idOwner);
        if (owner == null) {
            throw new OwnerNotFoundException("Owner with ID " + idOwner + " not found.");
        }

        Tamagotchi newTamagotchi = new Tamagotchi(owner, name);
        System.out.println("\nIN adoptionSERVICE\nowner is " + newTamagotchi.owner);
        System.out.println("\name is " + newTamagotchi.name);
        //add compte bancaire
        this.tamagotchiDAO.addTamagotchi(newTamagotchi);
        return newTamagotchi;
    }
    public Tamagotchi updateTamagotchiOwner(Integer tamagotchiId, Integer ownerId) throws TamagotchiNotFoundException, TamagotchiHasOwner {
        Tamagotchi tamagotchi = this.tamagotchiDAO.getTamagotchiById(tamagotchiId);
        if (tamagotchi == null) throw new TamagotchiNotFoundException("Tamagotchi with ID "+tamagotchiId+" not found.");
        if (tamagotchi.owner != null) throw new TamagotchiHasOwner("Tamagotchi " + tamagotchiId + " can't be adopted, it already has an owner : " + tamagotchi.owner.getUsername());
        tamagotchi.setOwner(ownerDAO.getOwner(ownerId));
        this.tamagotchiDAO.updateTamagotchi(tamagotchi);
        return tamagotchi;

    }

    @Transactional
    public Collection<Tamagotchi> getTamagotchis(Boolean hasOwner) {
        String query;

        if (hasOwner != null) {
            query = hasOwner ? "SELECT t FROM Tamagotchi t WHERE t.owner IS NOT NULL" : "SELECT t FROM Tamagotchi t WHERE t.owner IS NULL";
        } else {
            // If hasOwner is not set, return all Tamagotchis
            query = "SELECT t FROM Tamagotchi t";
        }

        return em.createQuery(query, Tamagotchi.class).getResultList();
    }


}