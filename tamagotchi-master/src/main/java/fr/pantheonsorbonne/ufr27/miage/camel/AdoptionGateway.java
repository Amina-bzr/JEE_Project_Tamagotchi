package fr.pantheonsorbonne.ufr27.miage.camel;


//import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi; u cant so reference it with full path
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.model.Owner;
import fr.pantheonsorbonne.ufr27.miage.service.AdoptionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class AdoptionGateway {
    @Inject
    AdoptionService AdoptionService;

    @Inject
    CamelContext camelContext;

    public fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi addTamagotchi(fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi tamagotchi) {
        this.AdoptionService.addTamagotchiService(tamagotchi.getName(), tamagotchi.getOwner());
        return tamagotchi;
    }

    public void removeTamagotchiFromOwner(fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi tamagotchi) {
        this.AdoptionService.updateTamagotchiOwner(tamagotchi.getId(), tamagotchi.getOwner());
    }
}