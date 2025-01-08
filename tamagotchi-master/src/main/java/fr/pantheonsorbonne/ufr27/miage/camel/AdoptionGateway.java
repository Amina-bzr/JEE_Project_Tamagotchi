package fr.pantheonsorbonne.ufr27.miage.camel;


//import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi; u cant so reference it with full path
import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import fr.pantheonsorbonne.ufr27.miage.service.AdoptionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;

@ApplicationScoped
public class AdoptionGateway {
    @Inject
    AdoptionService AdoptionService;

    @Inject
    CamelContext camelContext;

    public TamagotchiDTO addTamagotchi(TamagotchiDTO tamagotchi) {
        this.AdoptionService.addTamagotchiService(tamagotchi.getName(), tamagotchi.getOwner());
        return tamagotchi;
    }

    public void removeTamagotchiFromOwner(TamagotchiDTO tamagotchi) {
        this.AdoptionService.updateTamagotchiOwner(tamagotchi.getId(), tamagotchi.getOwner());
    }
}