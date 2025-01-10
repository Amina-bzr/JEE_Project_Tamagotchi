package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Override
    public void configure() throws Exception {
        // Route pour envoyer un cadeau
        from("direct:sendGift")
                .log("Envoi d'un cadeau magique : ${body}")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "tamagotchiGift");

        // Route pour envoyer une alerte magique
        from("direct:sendAlert")
                .log("Envoi d'une alerte magique : ${body}")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "magicalAlert");


        // Route pour retirer un Tamagotchi négligé
        from("direct:removeNeglectedTamagotchi")
                .log("Appel au service Adoption pour retirer le Tamagotchi ID: ${body}")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "TamagotchiToRemove");
    }
}