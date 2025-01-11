package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.dto.TamagotchiDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;



@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    AdoptionGateway adoptionGateway;


    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        from("sjms2:" + jmsPrefix + "removeTamagotchiFromOwner")
                .unmarshal().json(TamagotchiDTO.class)
                .bean(adoptionGateway, "removeTamagotchiFromOwner");


        //SERVICE ADOPT -----> FEE
        from("direct:TamagotchiAdopted")//please give him a gift !
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "TamagotchiAdopted");

    }

}
