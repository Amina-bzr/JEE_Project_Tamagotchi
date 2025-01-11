package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;


import fr.pantheonsorbonne.ufr27.miage.dto.*;
import org.apache.camel.model.RouteDefinition;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    AdoptionGateway adoptionGateway;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        RouteDefinition removeTamagotchiFromOwner = from("sjms2:" + jmsPrefix + "TamagotchiAdopted")
                .unmarshal().json(TamagotchiDTO.class)
                .bean(adoptionGateway, "removeTamagotchiFromOwner");


    }
}
