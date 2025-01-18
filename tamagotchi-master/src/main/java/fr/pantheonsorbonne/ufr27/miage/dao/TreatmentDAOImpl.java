package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Treatment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TreatmentDAOImpl implements TreatmentDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public List<Treatment> getAllTreatments() {
        return em.createQuery("SELECT t FROM Treatment t", Treatment.class).getResultList();
    }

    @Override
    @Transactional
    public Treatment getTreatmentById(Integer idTreatment) {
        return em.find(Treatment.class, idTreatment);
    }

    @Override
    @Transactional
    public Treatment addTreatment(Treatment treatment) {
        em.persist(treatment);
        return treatment;
    }

    @Override
    @Transactional
    public List<Treatment> getTreatmentsByDisease(String disease) {
        return em.createQuery("SELECT t FROM Treatment t WHERE t.disease = :disease", Treatment.class)
                .setParameter("disease", disease)
                .getResultList();
    }
}
