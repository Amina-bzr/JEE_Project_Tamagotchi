package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Owner;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {

    private final EntityManager em;

    // Constructor to inject EntityManager
    public OwnerDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Owner addOwner(Owner owner) {
        em.persist(owner);  // Add new owner to the database
        return owner;
    }

    @Override
    @Transactional
    public Owner getOwner(Integer idOwner) {
        return em.find(Owner.class, idOwner);  // Fetch owner by id
    }

    @Override
    @Transactional
    public List<Owner> getOwners() {
        return em.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();  // Get list of all owners
    }

    @Override
    @Transactional
    public void deleteOwner(Integer idOwner) {
        Owner owner = getOwner(idOwner);
        if (owner != null) {
            em.remove(owner);  // Remove owner by id
        } else {
            System.out.println("Owner with ID " + idOwner + " does not exist.");
        }
    }

    @Override
    @Transactional
    public Owner updateOwner(Owner owner) {
        return em.merge(owner);  // Update existing owner details
    }
}
