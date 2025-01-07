package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi;
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

        /* create a tamagothi*/
       /* from("sjms2:" + jmsPrefix + "createTamagothi")
                .unmarshal().json(Tamagotchi.class)
                .bean(adoptionGateway, "addTamagotchi")
         */

        /*from("sjms2:" + jmsPrefix + "createTamagothi")
                .autoStartup(isRouteEnabled)
                .unmarshal().json(Tamagotchi.class)
                .bean(adoptionGateway, "addTamagotchi")
                .log("received following information from user service in for tamagotchi creation request: ${body}");

         */


        from("sjms2:" + jmsPrefix + "removeTamagotchiFromOwner")
                .unmarshal().json(Tamagotchi.class)
                .bean(adoptionGateway, "removeTamagotchiFromOwner");

    }



}
