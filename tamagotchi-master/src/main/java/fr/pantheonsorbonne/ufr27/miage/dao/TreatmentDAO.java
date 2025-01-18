package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Treatment;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TreatmentDAO {

    @Transactional
    List<Treatment> getAllTreatments();

    @Transactional
    Treatment getTreatmentById(Integer idTreatment);

    @Transactional
    Treatment addTreatment(Treatment treatment);

    @Transactional
    List<Treatment> getTreatmentsByDisease(String disease);
}
